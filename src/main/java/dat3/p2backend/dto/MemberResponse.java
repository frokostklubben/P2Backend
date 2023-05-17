package dat3.p2backend.dto;

import dat3.p2backend.entity.Member;
import dat3.p2backend.entity.Result;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {
  String email;
  Double personHeight;
  Boolean isFemale;
  Boolean isColdSensitive;
  Double environmentTemperatureMin;
  Double minCost;
  Double maxCost;
  String innerMaterial;
  Boolean isInStore;


  /*public MemberResponse(Member member) {
    this.email = member.getEmail();
    this.personHeight = member.getPersonHeight();
    this.isFemale = member.getIsFemale();
    this.isColdSensitive = member.getIsColdSensitive();

  }*/


  public MemberResponse(Member member, Result result) {
    this.email = member.getEmail();
    this.personHeight = member.getPersonHeight();
    this.isFemale = member.getIsFemale();
    this.isColdSensitive = member.getIsColdSensitive();
    /*this.environmentTemperatureMin = member.getResult().getEnvironmentTemperatureMin();
    this.minCost = member.getResult().getMinCost();
    this.maxCost = member.getResult().getMaxCost();
    this.innerMaterial = member.getResult().getInnerMaterial();
    this.isInStore = member.getResult().getIsInStore();
*/
   this.environmentTemperatureMin = result.getEnvironmentTemperatureMin();
  this.minCost = result.getMinCost();
    this.maxCost = result.getMaxCost();
    this.innerMaterial = result.getInnerMaterial();
    this.isInStore = result.getIsInStore();
  }
}
