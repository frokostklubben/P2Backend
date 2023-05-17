package dat3.p2backend.service;

import dat3.p2backend.dto.MemberRequest;
import dat3.p2backend.dto.MemberResponse;
import dat3.p2backend.entity.Member;
import dat3.p2backend.entity.Result;
import dat3.p2backend.repository.MemberRepository;
import dat3.p2backend.repository.ResultRepository;
import dat3.security.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class MemberService {

MemberRepository memberRepository;
ResultRepository resultRepository;
PasswordEncoder passwordEncoder;

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

    Member member = new Member(request, pw);
    Result result = new Result(request);
    member.setResult(result);
    member.addRole(user);
    resultRepository.save(result);
    memberRepository.save(member);

    return new MemberResponse(member, result);
  }

  public MemberResponse getMember(String username) {
    Member member = memberRepository.findById(username).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Brugeren kunne ikke findes."));
   return new MemberResponse(member, member.getResult());
  }

  public MemberResponse deleteById(String id) {
    Member member = memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    memberRepository.deleteById(id);
    return new MemberResponse(member, member.getResult());
  }
}
