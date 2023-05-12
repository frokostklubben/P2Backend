package dat3.p2backend.dto;

import dat3.p2backend.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {
  String email;
  Double personHeight;
  Boolean isFemale;
  Boolean isColdSensitive;

  public MemberResponse(Member member) {
    this.email = member.getEmail();
    this.personHeight = member.getPersonHeight();
    this.isFemale = member.getIsFemale();
    this.isColdSensitive = member.getIsColdSensitive();
  }
}
