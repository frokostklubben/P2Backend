package dat3.p2backend.service;

import dat3.p2backend.dto.MemberRequest;
import dat3.p2backend.repository.MemberRepository;
import dat3.security.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@DataJpaTest
class MemberServiceTest {

  @Autowired
  MemberRepository memberRepository;

  PasswordEncoder passwordEncoder;

  MemberService memberService;



  @BeforeEach
  void setUp() {
    memberService = new MemberService(memberRepository,passwordEncoder);
  }

  @Test
  void addUserWithRoles() {
    MemberRequest memberRequest = new MemberRequest("test@gmail.com","test12",193.5,true,false);
//    memberService.addUserWithRoles(memberRequest, Role.USER);

  }
}