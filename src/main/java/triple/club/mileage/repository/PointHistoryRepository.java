package triple.club.mileage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import triple.club.mileage.domain.PointHistory;
import triple.club.mileage.domain.Review;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
}
