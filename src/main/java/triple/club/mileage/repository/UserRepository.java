package triple.club.mileage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import triple.club.mileage.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
