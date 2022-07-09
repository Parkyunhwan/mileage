package triple.club.mileage.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import triple.club.mileage.domain.enums.EventType;
import triple.club.mileage.service.event.EventAction;
import triple.club.mileage.service.review.ReviewAction;

@RequiredArgsConstructor
@Component
public class EventActionFactory {

    private final ReviewAction reviewAction;

    public EventAction getEventAction(EventType eventType) {
        if (EventType.REVIEW.equals(eventType)) {
            return reviewAction;
        }
        return null;
    }
}
