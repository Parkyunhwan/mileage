package triple.club.mileage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import triple.club.mileage.domain.Event;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.service.EventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/events")
    public ResponseEntity actionEvent(@RequestBody EventRequestDTO eventRequestDTO) {

        eventService.saveEvent(eventRequestDTO);
        return ResponseEntity.ok().build();
    }
}
