package triple.club.mileage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import triple.club.mileage.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
