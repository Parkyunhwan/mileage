package triple.club.mileage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import triple.club.mileage.domain.Event;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;


    public Long saveEvent(EventRequestDTO eventRequestDTO) {

        // ADD, REVIEW
        EventType eventType = EventType.selectEventType(eventRequestDTO.getType());
        ActionType actionType = ActionType.selectActionType(eventRequestDTO.getAction());
        String reviewId = eventRequestDTO.getReviewId();

        Event event = Event.createEvent()
                .eventType(eventType)
                .actionType(actionType)
                .actionEventId(reviewId)
                .build();

        Event savedEvent = eventRepository.save(event);
        return savedEvent.getId();
    }
}
