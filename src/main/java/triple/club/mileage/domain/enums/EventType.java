package triple.club.mileage.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventType {
    REVIEW("REVIEW");

    private final String tableValue;

    public static EventType selectEventType(String value) {
        return EventType.valueOf(value);
    }
}
