package dat3.p2backend.repository;

import dat3.p2backend.entity.SleepingBag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SleepingBagRepository extends JpaRepository<SleepingBag, Integer> {
}
