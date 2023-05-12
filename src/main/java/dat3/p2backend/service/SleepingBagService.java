package dat3.p2backend.service;

import dat3.p2backend.dto.SleepingBagRequest;
import dat3.p2backend.dto.SleepingBagResponse;
import dat3.p2backend.entity.ImageLink;
import dat3.p2backend.entity.SleepingBag;
import dat3.p2backend.repository.ImageLinkRepository;
import dat3.p2backend.repository.SleepingBagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class SleepingBagService {
    SleepingBagRepository sleepingBagRepository;

    ImageLinkRepository imageLinkRepository;

    public SleepingBagService(SleepingBagRepository sleepingBagRepository, ImageLinkRepository imageLinkRepository) {
        this.sleepingBagRepository = sleepingBagRepository;
        this.imageLinkRepository = imageLinkRepository;
    }


  public List<SleepingBagResponse> getSleepingBags(SleepingBagRequest sleepingBagRequest) {

    List<SleepingBag> sleepingBags = sleepingBagRepository.findAll().stream()
        .filter(sleepingBag -> filterByTemperature(sleepingBag, sleepingBagRequest))
        .filter(sleepingBag -> filterByCost(sleepingBag, sleepingBagRequest))
        .filter(sleepingBag -> filterByInnerMaterial(sleepingBag, sleepingBagRequest))
        .filter(sleepingBag -> filterByPersonHeight(sleepingBag, sleepingBagRequest))
        .filter(sleepingBag -> filterByGender(sleepingBag, sleepingBagRequest))
        .filter(sleepingBag -> filterByStockLocation(sleepingBag, sleepingBagRequest))
        .toList();

    if (sleepingBagRequest.getPersonHeight() != null) {
      List<SleepingBagResponse> sleepingBagsresult = sleepingBags.stream()
          .sorted(Comparator.comparing(SleepingBag::getModel).thenComparing(SleepingBag::getPersonHeight))
          .filter(distinctByKey(SleepingBag::getModel))
          .map(sleepingBag -> new SleepingBagResponse(sleepingBag, findImageURL(sleepingBag)))
          .toList();
      return sleepingBagsresult;
    }

    return sleepingBags.stream()
            .map(sleepingBag -> new SleepingBagResponse(sleepingBag, findImageURL(sleepingBag)))
            .toList();

  }

  private String findImageURL(SleepingBag sleepingBag) {
      Optional<ImageLink> imageLink = imageLinkRepository.findById(sleepingBag.getSku());

      if (imageLink.isPresent()) {
          return imageLink.get().getImageURL();
      }
      else {
          return "https://cdn.fotoagent.dk/accumolo/production/themes/friluftsland_2021/images/noimage_1_small.jpg";
      }
  }

  private boolean filterByStockLocation(SleepingBag sleepingBag, SleepingBagRequest sleepingBagRequest) {

      if (sleepingBagRequest.getIsInStore() != null){

          return sleepingBag.getStockLocation().equals("Butik");

      }
      return true;
  }

  private boolean filterByTemperature(SleepingBag sleepingBag, SleepingBagRequest sleepingBagRequest) {
    if (sleepingBagRequest.getIsColdSensitive() == null || sleepingBagRequest.getIsColdSensitive()) {
      return sleepingBagRequest.getEnvironmentTemperatureMin() == null || sleepingBag.getComfortTemp() <= sleepingBagRequest.getEnvironmentTemperatureMin();
    } else {
      return sleepingBagRequest.getEnvironmentTemperatureMin() == null || sleepingBag.getLowerLimitTemp() <= sleepingBagRequest.getEnvironmentTemperatureMin();
    }
  }

  private boolean filterByCost(SleepingBag sleepingBag, SleepingBagRequest sleepingBagRequest) {
    return sleepingBagRequest.getMaxCost() == null || sleepingBagRequest.getMinCost() == null || sleepingBag.getCost() >= sleepingBagRequest.getMinCost() &&
        sleepingBag.getCost() <= sleepingBagRequest.getMaxCost();
  }

  private boolean filterByInnerMaterial(SleepingBag sleepingBag, SleepingBagRequest sleepingBagRequest) {
    return sleepingBagRequest.getInnerMaterial() == null || sleepingBag.getInnerMaterial().equals(sleepingBagRequest.getInnerMaterial());
  }

  private boolean filterByPersonHeight(SleepingBag sleepingBag, SleepingBagRequest sleepingBagRequest) {

      if (sleepingBagRequest.getPersonHeight() != null) {
        return (sleepingBag.getPersonHeight() >= sleepingBagRequest.getPersonHeight()) &&
            (sleepingBag.getPersonHeight() - sleepingBagRequest.getPersonHeight() <= 15);
      }
    return true;
  }

  private boolean filterByGender(SleepingBag sleepingBag, SleepingBagRequest sleepingBagRequest) {

      if(sleepingBagRequest.getIsFemale() != null){

        if (!sleepingBagRequest.getIsFemale()) {
          return !sleepingBag.getIsFemale();
        } else {
          return sleepingBag.getIsFemale() || !sleepingBag.getModel().contains("(M)");
        }
      }
      return true;
  }

    // Taget fra Stack overflow
    // https://stackoverflow.com/questions/23699371/java-8-distinct-by-property
  public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
  }

}
