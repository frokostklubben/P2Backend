package dat3.p2backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
