package triple.club.mileage.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.service.event.EventAction;
import triple.club.mileage.factory.ReviewServiceFactory;

@RequiredArgsConstructor
@Component
public class ReviewAction implements EventAction {

    private final ReviewServiceFactory reviewServiceFactory;

    @Override
    public String action(EventRequestDTO eventRequestDTO){
        ActionType actionType = ActionType.valueOf(eventRequestDTO.getAction());
        ReviewService service = reviewServiceFactory.getService(actionType);
        service.doService(eventRequestDTO);
        return eventRequestDTO.getReviewId();
    }

    @Override
    public EventType getType() {
        return EventType.REVIEW;
    }
}
