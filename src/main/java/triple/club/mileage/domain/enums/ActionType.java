package triple.club.mileage.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ActionType {
    ADD("ADD"), MOD("MOD"), DELETE("DELETE");

    private final String eventAction;

    public static ActionType selectActionType(String value) {
        return ActionType.valueOf(value);
    }
}
