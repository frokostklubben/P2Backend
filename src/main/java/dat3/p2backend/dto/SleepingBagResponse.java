package dat3.p2backend.dto;

import dat3.p2backend.entity.ImageLink;
import dat3.p2backend.entity.SleepingBag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SleepingBagResponse {

  String brand;
  String model;
  Integer sku;
  Double cost;
  Integer personHeight;
  Double comfortTemp;
  Double lowerLimitTemp;
  String innerMaterial;
  Integer productWeight;
  String stockLocation;
  String note;
  String imageURL;

  public SleepingBagResponse(SleepingBag sleepingBag, String imageURL) {
    this.sku = sleepingBag.getSku();
    this.brand = sleepingBag.getBrand();
    this.model = sleepingBag.getModel();
    this.comfortTemp = sleepingBag.getComfortTemp();
    this.lowerLimitTemp = sleepingBag.getLowerLimitTemp();
    this.cost = sleepingBag.getCost();
    this.personHeight = sleepingBag.getPersonHeight();
    this.innerMaterial = sleepingBag.getInnerMaterial();
    this.productWeight = sleepingBag.getProductWeight();
    this.stockLocation = sleepingBag.getStockLocation();
    this.note = sleepingBag.getNote();
    this.imageURL = imageURL;
  }


}


