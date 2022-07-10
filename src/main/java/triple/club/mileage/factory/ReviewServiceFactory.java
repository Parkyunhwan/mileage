package triple.club.mileage.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.service.review.ReviewService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ReviewServiceFactory {
    private final ApplicationContext ac;
    private static final Map<ActionType, ReviewService> serviceMap = new HashMap<>();

    @PostConstruct
    void postConstruct() {
        Map<String, ReviewService> beansOfType = ac.getBeansOfType(ReviewService.class);
        Collection<ReviewService> services = beansOfType.values();
        for (ReviewService service : services) {
            serviceMap.put(service.getType(), service);
        }
    }

    public ReviewService getService(ActionType actionType) {
        return serviceMap.get(actionType);
    }
}
