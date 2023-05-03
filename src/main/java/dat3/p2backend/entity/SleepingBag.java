package dat3.p2backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class SleepingBag {
  String brand;
  String model;
  @Id
  Integer sku;
  Double cost;
  Integer personHeight;
  Double comfortTemp;
  Double lowerLimitTemp;
  String innerMaterial;
  String downFillPower; //650, 700, 800 or 850+

  Integer productWeight;
  String stockLocation;
  Boolean isFemale;

  public SleepingBag(SleepingBagExternal sleepingBagExternal) {
    this.brand = sleepingBagExternal.getBrand();
    this.model = sleepingBagExternal.getModel();
    this.sku = parseInt(sleepingBagExternal.getSku());
    //this.cost = parseDouble(sleepingBagExternal.getCost());
    //this.personHeight = parseInt(sleepingBagExternal.getPersonHeight());
    //this.comfortTemp = parseDouble(sleepingBagExternal.getComfortTemp());
    //this.lowerLimitTemp = parseDouble(sleepingBagExternal.getLowerLimitTemp());


    //this.innerMaterial = sleepingBagExternal.getInnerMaterial().substring(
     //       0,
       //     sleepingBagExternal.getInnerMaterial().indexOf("(")-1).trim();


    //this.downFillPower = sleepingBagExternal.getInnerMaterial().substring(
    //        sleepingBagExternal.getInnerMaterial().indexOf("(")+1,
    //        sleepingBagExternal.getInnerMaterial().indexOf(")"));

    this.productWeight = parseInt(sleepingBagExternal.getProductWeight());


    this.isFemale = sleepingBagExternal.getModel().contains("(W)");


  }
}