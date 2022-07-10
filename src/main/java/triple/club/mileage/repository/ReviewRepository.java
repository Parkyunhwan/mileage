package triple.club.mileage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import triple.club.mileage.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
