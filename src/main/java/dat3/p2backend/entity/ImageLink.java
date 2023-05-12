package dat3.p2backend.entity;

import jakarta.persistence.*;
import lombok.*;

import static java.lang.Integer.parseInt;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class ImageLink {

  @Id
  Integer skuId;
  String imageURL;

  @OneToOne(mappedBy = "imageLink")
  SleepingBag sleepingBag;


  public ImageLink(String skuId, String imageURL) {
    this.skuId = parseInt(skuId);
    this.imageURL = imageURL;
  }
}
