package dat3.p2backend.entity;

import jakarta.persistence.*;
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

//@SecondaryTable(name = "image", pkJoinColumns=@PrimaryKeyJoinColumn(name="image.sku_id", referencedColumnName="sku"))
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

  String note;
/*  @Column(table="image", name="imageLink", nullable=true)*/

  @OneToOne
  @PrimaryKeyJoinColumn(name = "sku")
  private ImageLink imageLink;


  public SleepingBag(SleepingBagExternal sleepingBagExternal) {
    this.brand = sleepingBagExternal.getBrand();
    this.model = sleepingBagExternal.getModel();
    this.sku = parseInt(sleepingBagExternal.getSku());
    this.cost = parseDouble(sleepingBagExternal.getCost().replaceAll(",","."));


    if (sleepingBagExternal.getPersonHeight().contains("-")) {
      this.personHeight = parseInt(sleepingBagExternal.getPersonHeight().substring(
              sleepingBagExternal.getPersonHeight().indexOf("-") + 1));
    }
    else {
      this.personHeight = parseInt(sleepingBagExternal.getPersonHeight());
    }

    try {
      this.comfortTemp = parseDouble(sleepingBagExternal.getComfortTemp().replaceAll(",","."));
    } catch (Exception e) {
      this.comfortTemp = parseDouble(sleepingBagExternal.getRecommendedTemp());
    }

    try {
      this.lowerLimitTemp = parseDouble(sleepingBagExternal.getLowerLimitTemp().replaceAll(",","."));
    } catch (Exception e) {
      this.lowerLimitTemp = parseDouble(sleepingBagExternal.getRecommendedTemp());
    }

    if(lowerLimitTemp.equals(comfortTemp)) {
      this.note = "OBS! Angivet temperature er ikke målt efter EN-teststandard. Snak med en sælger, hvis du er i tvivl.";
    }

    if (sleepingBagExternal.getInnerMaterial().contains("(")) {
        this.downFillPower = sleepingBagExternal.getInnerMaterial().substring(
              sleepingBagExternal.getInnerMaterial().indexOf("(")+1,
              sleepingBagExternal.getInnerMaterial().indexOf(")"));
    }
    else {
        this.downFillPower = "";
    }

    if (this.downFillPower != "") {
        this.innerMaterial = sleepingBagExternal.getInnerMaterial().substring(
                0,
                sleepingBagExternal.getInnerMaterial().indexOf("(")-1).trim();
    }
    else {
      this.innerMaterial = sleepingBagExternal.getInnerMaterial();
    }

    this.productWeight = parseInt(sleepingBagExternal.getProductWeight());
    this.stockLocation = sleepingBagExternal.getStockLocation();
    this.isFemale = sleepingBagExternal.getModel().contains("(W)");

  }
}