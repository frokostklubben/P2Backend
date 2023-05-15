package dat3.p2backend.api;


import dat3.p2backend.dto.MemberRequest;
import dat3.p2backend.dto.MemberResponse;
import dat3.p2backend.service.MemberService;
import dat3.security.entity.Role;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/member")
public class MemberController {

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  MemberService memberService;

  @PostMapping
  public MemberResponse addUserWithRoles(@RequestBody MemberRequest request) {
    return memberService.addUserWithRoles (request, Role.USER);
  }

  @DeleteMapping()
  public void deleteMemberById(Principal p) {
    memberService.deleteById(p.getName());
  }


}
