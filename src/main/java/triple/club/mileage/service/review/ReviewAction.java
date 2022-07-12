package triple.club.mileage.service.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.factory.ReviewServiceFactory;
import triple.club.mileage.service.event.EventAction;

@RequiredArgsConstructor
@Component
@Slf4j
public class ReviewAction implements EventAction {

    private final ReviewServiceFactory reviewServiceFactory;

    @Override
    @Retryable(include = OptimisticLockingFailureException.class, maxAttempts = 3)
    public String action(EventRequestDTO eventRequestDTO){
        ActionType actionType = ActionType.valueOf(eventRequestDTO.getAction());
        ReviewService service = reviewServiceFactory.getService(actionType);
        try {
            service.doService(eventRequestDTO);
        } catch (OptimisticLockingFailureException exception) {
            log.warn("낙관적 락이 실패하였습니다.");
            throw exception;
        }
        return eventRequestDTO.getReviewId();
    }

    @Override
    public EventType getType() {
        return EventType.REVIEW;
    }
}
