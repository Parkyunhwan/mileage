package triple.club.mileage.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PointType {
    SAVE("적립"), USE("차감");

    private final String description;
}
