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
        //initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        private static final String PLACE_ID1 = "2e4baf1c-5acb-4efb-a1af-eddada31b00f";
        private static final String PLACE_ID2 = "2e4baf1c-5acb-4efb-a1af-eddada31b00e";
        private static final String USER_ID1 = "3ede0ef2-92b7-4817-a5f3-0c575361f745";
        private static final String USER_ID2 = "ktde0ef2-92b7-4817-a5f3-0c575361f746";
        private static final String USER_ID3 = "acde0ef2-92b7-4817-a5f3-0c575361f746";

        public void dbInit1() {
            Place place = createPlace(PLACE_ID1, "첫번째장소");
            em.persist(place);

            Place place2 = createPlace(PLACE_ID2, "두번째장소");
            em.persist(place2);

            User user = createUser(USER_ID1, "USER1");
            em.persist(user);

            User user2 = createUser(USER_ID2, "USER2");
            em.persist(user2);

            User user3 = createUser(USER_ID3, "USER3");
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
                User user = createUser(stri, "USER" + stri);
                em.persist(user);
            }
        }

    }
}
