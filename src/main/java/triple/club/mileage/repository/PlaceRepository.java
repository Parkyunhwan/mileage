package triple.club.mileage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import triple.club.mileage.domain.Place;
import triple.club.mileage.domain.PointHistory;

public interface PlaceRepository extends JpaRepository<Place, String> {
}
