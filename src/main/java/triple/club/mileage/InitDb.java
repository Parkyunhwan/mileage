package triple.club.mileage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import triple.club.mileage.domain.Place;
import triple.club.mileage.domain.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        private static final String PLACE_ID1 = "1";
        private static final String USER_ID1 = "1";
        private static final String USER_ID2 = "2";
        public void dbInit1() {
            Place place = createPlace(PLACE_ID1, "첫번째장소");
            em.persist(place);

            User user = createUser(USER_ID1, "USER1");
            em.persist(user);

            User user2 = createUser(USER_ID2, "USER1");
            em.persist(user2);
        }

        private Place createPlace(String placeId, String name) {
            return Place.builder().id(placeId).name(name).build();
        }

        private User createUser(String userId, String name) {
            return User.builder().id(userId).name(name).pointScore(0L).build();
        }


        public void dbInit2() {
            for (int i = 3; i < 11; i++) {
                String stri = String.valueOf(i);
                User user = createUser(stri, "name_" + stri);
                em.persist(user);
            }
        }

    }
}
