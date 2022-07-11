package triple.club.mileage.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import triple.club.mileage.domain.Event;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.factory.EventActionFactory;
import triple.club.mileage.repository.EventRepository;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventActionFactory eventActionFactory;



    public Long actionEvent(EventRequestDTO eventRequestDTO) {

        // 이벤트와 관련된 로직 시작
        EventAction eventAction = eventActionFactory.getEventAction(EventType.valueOf(eventRequestDTO.getType()));
        String actionEventId = eventAction.action(eventRequestDTO);

        // 이벤트 저장
        Event event = Event.createEvent().eventType(EventType.valueOf(eventRequestDTO.getType()))
                .actionType(ActionType.valueOf(eventRequestDTO.getAction())).actionEventId(actionEventId)
                .build();

        Event savedEvent = eventRepository.save(event);
        return savedEvent.getId();
    }
}
