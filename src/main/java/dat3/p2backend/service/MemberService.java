package dat3.p2backend.service;

import dat3.p2backend.dto.MemberRequest;
import dat3.p2backend.dto.MemberResponse;
import dat3.p2backend.entity.Member;
import dat3.p2backend.entity.Result;
import dat3.p2backend.repository.MemberRepository;
import dat3.p2backend.repository.ResultRepository;
import dat3.security.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class MemberService {

MemberRepository memberRepository;
ResultRepository resultRepository;
private PasswordEncoder passwordEncoder;

  public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, ResultRepository resultRepository) {
    this.memberRepository = memberRepository;
    this.passwordEncoder = passwordEncoder;
    this.resultRepository = resultRepository;
  }

  public MemberResponse addUserWithRoles(MemberRequest request, Role user) {

    if(request.getPassword().isEmpty() || request.getEmail().isEmpty() || !request.getEmail().contains("@")
      || !request.getEmail().contains(".")){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Venligst udfyld email og password");
    }

        if(memberRepository.existsById(request.getEmail())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email er allerede registrered");
    }
    String pw = passwordEncoder.encode(request.getPassword());

    Member member = new Member(pw, request.getEmail(), request.getPersonHeight(), request.getIsFemale(), request.getIsColdSensitive());
    Result result = new Result(request.getEnvironmentTemperatureMin(), request.getMinCost(), request.getMaxCost(), request.getInnerMaterial(), request.getIsInStore());
    member.setResult(result);
    member.addRole(user);
    resultRepository.save(result);
    memberRepository.save(member);
    return new MemberResponse(member);
  }
}
