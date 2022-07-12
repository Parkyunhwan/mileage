package triple.club.mileage.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import triple.club.mileage.domain.Event;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.dto.EventResponseDTO;
import triple.club.mileage.factory.EventActionFactory;
import triple.club.mileage.repository.EventRepository;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventActionFactory eventActionFactory;



    @Transactional
    public EventResponseDTO actionEvent(EventRequestDTO eventRequestDTO) {
        // 이벤트와 관련된 로직 시작
        EventAction eventAction = eventActionFactory.getEventAction(EventType.valueOf(eventRequestDTO.getType()));
        String actionEventId = eventAction.action(eventRequestDTO);

        // 이벤트 저장 (actionEventId는 ActionType으로 어떤 Action의 Id인지 구분)
        Event event = Event.createEvent().eventType(EventType.valueOf(eventRequestDTO.getType()))
                .actionType(ActionType.valueOf(eventRequestDTO.getAction())).actionEventId(actionEventId)
                .build();

        Event savedEvent = eventRepository.save(event);
        return EventResponseDTO.builder().eventId(savedEvent.getId()).build();
    }
}
