package dat3.p2backend.repository;

import dat3.p2backend.entity.ImageLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageLinkRepository extends JpaRepository<ImageLink, Integer> {
}
