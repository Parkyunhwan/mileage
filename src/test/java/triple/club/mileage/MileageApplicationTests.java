package triple.club.mileage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import triple.club.mileage.domain.Place;
import triple.club.mileage.domain.User;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.service.event.EventService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Slf4j
class MileageApplicationTests {

    @Autowired
    private EventService eventService;

    @Test
    @Transactional
    void contextLoads() {
        List<EventRequestDTO> eventRequestDTOS = new ArrayList<>();
        for (int i = 3; i < 11; i++) {
            String stri = String.valueOf(i);
            EventRequestDTO dto = EventRequestDTO.builder().reviewId(stri).action("ADD").content("AA").attachedPhotoIds(List.of("aa")).userId(stri).placeId("1").type("REVIEW").build();
            eventRequestDTOS.add(dto);
        }

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 8; i++) {
            EventRequestDTO dto = eventRequestDTOS.get(i);
            service.execute(() -> {
                try {
                    eventService.actionEvent(dto);
                } catch (Exception e) {
                    log.error("ERROR:", e);
                }
            });
        }
        eventService.actionEvent(eventRequestDTOS.get(0));
    }

    private Place createPlace(String placeId, String name) {
        return Place.builder().id(placeId).name(name).build();
    }

    private User createUser(String userId, String name) {
        return User.builder().id(userId).name(name).pointScore(0L).build();
    }

}
