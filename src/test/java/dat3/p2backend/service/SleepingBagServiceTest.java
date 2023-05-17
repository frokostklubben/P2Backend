package dat3.p2backend.service;

import dat3.p2backend.dto.SleepingBagRequest;
import dat3.p2backend.dto.SleepingBagResponse;
import dat3.p2backend.entity.ImageLink;
import dat3.p2backend.entity.SleepingBag;
import dat3.p2backend.repository.ImageLinkRepository;
import dat3.p2backend.repository.SleepingBagExternalRepository;
import dat3.p2backend.repository.SleepingBagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SleepingBagServiceTest {


  @Autowired
  SleepingBagRepository sleepingBagRepository;

  @Autowired
  SleepingBagExternalRepository sleepingBagExternalRepository;

  @Autowired
  ImageLinkRepository imageLinkRepository;

  SleepingBagService sleepingBagService;

  @BeforeEach
  void setUp(){
    SleepingBag s1 = new SleepingBag("TestBrand","TestModel",12,5000.0,180,5.0,0.0,"Dun",null,500,"Butik",true,null,null);
    SleepingBag s2 = new SleepingBag("TestBrand","TestModel",123,10000.0,190,5.0,0.0,"Fiber",null,500,"Butik",false,null,null);
    SleepingBag s3 = new SleepingBag("TestBrand","TestModel",1234,7500.0,170,5.0,0.0,"Dun",null,500,"Wider",false,null,null);
    sleepingBagRepository.save(s1);
    sleepingBagRepository.save(s2);
    sleepingBagRepository.save(s3);

    sleepingBagService = new SleepingBagService(sleepingBagRepository, imageLinkRepository);

  }

  @Test
  void getAllSleepingBags() {
    SleepingBagRequest sleepingBagRequest = new SleepingBagRequest();
    List<SleepingBagResponse> sleepingBagResponses = sleepingBagService.getSleepingBags(sleepingBagRequest);
    assertEquals(3, sleepingBagResponses.size());
  }

  @Test
  void filterByGenderFemale(){
    SleepingBagRequest sleepingBagRequest = new SleepingBagRequest(null,null,true,null,null,null,null,null);
    SleepingBag sleepingBag = sleepingBagRepository.findById(12).get();
    boolean result = sleepingBagService.filterByGender(sleepingBag,sleepingBagRequest);
    assertTrue(result);
  }

  @Test
  void filterByCost(){
    SleepingBagRequest sleepingBagRequest = new SleepingBagRequest(null,null,null,null,4000.0,5050.0,null,null);
    SleepingBag sleepingBag = sleepingBagRepository.findById(12).get();
    boolean result = sleepingBagService.filterByCost(sleepingBag,sleepingBagRequest);
    assertTrue(result);
  }

  @Test
  void filterByTemperatureNotSensitive(){
    SleepingBagRequest sleepingBagRequest = new SleepingBagRequest(null,4.0,null,false,null,null,null,null);
    SleepingBag sleepingBag = sleepingBagRepository.findById(12).get();
    boolean result = sleepingBagService.filterByTemperature(sleepingBag,sleepingBagRequest);
    assertTrue(result);
  }

  @Test
  void filterByTemperatureSensitive(){
    SleepingBagRequest sleepingBagRequest = new SleepingBagRequest(null,4.0,null,true,null,null,null,null);
    SleepingBag sleepingBag = sleepingBagRepository.findById(12).get();
    boolean result = sleepingBagService.filterByTemperature(sleepingBag,sleepingBagRequest);
    assertFalse(result);
  }

  @Test
  void filterByInnerMaterial(){
    SleepingBagRequest sleepingBagRequest = new SleepingBagRequest(null,null,null,null,null,null,"Dun",null);
    SleepingBag sleepingBag = sleepingBagRepository.findById(12).get();
    boolean result = sleepingBagService.filterByInnerMaterial(sleepingBag,sleepingBagRequest);
    assertTrue(result);
  }

  @Test
  void filterByPersonHeight(){
    SleepingBagRequest sleepingBagRequest = new SleepingBagRequest(180.0,null,null,null,null,null,null,null);
    SleepingBag sleepingBag = sleepingBagRepository.findById(12).get();
    boolean result = sleepingBagService.filterByPersonHeight(sleepingBag,sleepingBagRequest);
    assertTrue(result);
  }

  @Test
  void filterByStockLocation(){
    SleepingBagRequest sleepingBagRequest = new SleepingBagRequest(null,null,null,null,null,null,null,true);
    SleepingBag sleepingBag = sleepingBagRepository.findById(12).get();
    boolean result = sleepingBagService.filterByStockLocation(sleepingBag,sleepingBagRequest);
    assertTrue(result);
  }

  @Test
  void findImageURL(){
    String inputString = "www.test.dk";
    SleepingBag sleepingBag = sleepingBagRepository.findById(12).get();
    ImageLink imageLink = new ImageLink(String.valueOf(sleepingBag.getSku()),inputString);
    imageLinkRepository.save(imageLink);
    String result = sleepingBagService.findImageURL(sleepingBag);
    assertEquals(inputString,result);
  }

  @Test
  void noDuplicateHeightBag(){
    SleepingBagRequest sleepingBagRequest = new SleepingBagRequest(170.0,null,true,null,null,null,null,null);
    List<SleepingBagResponse> sleepingBagResponses = sleepingBagService.getSleepingBags(sleepingBagRequest);
    assertEquals(1, sleepingBagResponses.size());
  }



}