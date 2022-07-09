package triple.club.mileage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.service.event.EventService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/events")
    public ResponseEntity actionEvent(@RequestBody @Valid EventRequestDTO eventRequestDTO) {

        eventService.actionEvent(eventRequestDTO);
        return ResponseEntity.ok().build();
    }
}
