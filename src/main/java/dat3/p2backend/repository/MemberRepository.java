package dat3.p2backend.repository;

import dat3.p2backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository <Member, String> {
}
