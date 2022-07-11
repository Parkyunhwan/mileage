package triple.club.mileage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import triple.club.mileage.dto.ApiResponse;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.dto.EventResponseDTO;
import triple.club.mileage.service.event.EventService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/events")
    public ResponseEntity<ApiResponse> actionEvent(@RequestBody @Valid EventRequestDTO eventRequestDTO) {
        EventResponseDTO eventResponseDTO = eventService.actionEvent(eventRequestDTO);
        return ApiResponse.of("생성된 이벤트 ID값", eventResponseDTO);

    }
}
