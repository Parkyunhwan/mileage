package triple.club.mileage.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;
import triple.club.mileage.service.event.EventAction;
import triple.club.mileage.service.review.ReviewAction;
import triple.club.mileage.service.review.ReviewService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class EventActionFactory {

    private final ApplicationContext ac;
    private static final Map<EventType, EventAction> serviceMap = new HashMap<>();

    @PostConstruct
    void postConstruct() {
        Map<String, EventAction> beansOfType = ac.getBeansOfType(EventAction.class);
        Collection<EventAction> actions = beansOfType.values();
        for (EventAction action : actions) {
            serviceMap.put(action.getType(), action);
        }
    }

    public EventAction getEventAction(EventType eventType) {
        return serviceMap.get(eventType);
    }
}
