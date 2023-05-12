package dat3.p2backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.p2backend.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberRequest {

  private String email;
  private String password;
  Double personHeight;
  Boolean isFemale;
  Boolean isColdSensitive;

  // MemberRequest to member conversion
  public static Member getMemberEntity(MemberRequest m){
    return new Member(m.getPassword(),m.getEmail(),m.getPersonHeight(), m.isFemale, m.getIsColdSensitive());
  }


}