package entity;

import entity.enums.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private Type type;

    private long hostId;

    private long guestId;

    private long playerId;

    private int value;

    @ManyToOne()
    @JoinColumn(name = "Id")
    private Game game;

    public Event(Type type, long hostId, long guestId, long playerId, int value, Game game) {
        this.type = type;
        this.hostId = hostId;
        this.guestId = guestId;
        this.playerId = playerId;
        this.value = value;
        this.game = game;
    }

    public Event() {
    }
}
