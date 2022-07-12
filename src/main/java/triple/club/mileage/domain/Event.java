package triple.club.mileage.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;

import javax.persistence.*;

@Entity
@Table(name = "event_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    @Getter
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    private String actionEventId;

    @Builder(builderMethodName = "createEvent")
    private Event(EventType eventType, ActionType actionType, String actionEventId) {
        this.eventType = eventType;
        this.actionType = actionType;
        this.actionEventId = actionEventId;
    }
}
