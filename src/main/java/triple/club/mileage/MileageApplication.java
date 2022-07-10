package triple.club.mileage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MileageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MileageApplication.class, args);
    }

}
