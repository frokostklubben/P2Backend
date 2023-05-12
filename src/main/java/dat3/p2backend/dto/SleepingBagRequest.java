package dat3.p2backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SleepingBagRequest {

  Double personHeight;
  Double environmentTemperatureMin;
  Boolean isFemale;
  Boolean isColdSensitive;
  Double minCost;
  Double maxCost;
  String innerMaterial;
  Boolean isInStore;

}
