package triple.club.mileage.service.point;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import triple.club.mileage.domain.PointHistory;
import triple.club.mileage.domain.User;
import triple.club.mileage.domain.enums.PointEventType;
import triple.club.mileage.repository.PointHistoryRepository;

@Service
@RequiredArgsConstructor
public class PointHistoryService {
    private final PointHistoryRepository pointHistoryRepository;

    public void savePointHistory(PointEventType pointEventType, User user) {
        PointHistory pointHistory = PointHistory.builder()
                .point(pointEventType.getPoint())
                .pointEventType(pointEventType)
                .pointType(pointEventType.getPointType())
                .description(pointEventType.getDescription())
                .user(user)
                .build();
        pointHistoryRepository.save(pointHistory);
    }
}
