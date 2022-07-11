package triple.club.mileage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import triple.club.mileage.domain.PointHistory;
import triple.club.mileage.domain.Review;
import triple.club.mileage.domain.User;

import java.util.List;
import java.util.Optional;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
    Optional<List<PointHistory>> findByUser(User user);
}
