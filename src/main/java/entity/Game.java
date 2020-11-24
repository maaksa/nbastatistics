package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private long guestId;

    private long hostId;

    @OneToMany(mappedBy = "game")
    private List<Event> events;

    public Game(long guestId, long hostId, List<Event> events) {
        this.guestId = guestId;
        this.hostId = hostId;
        this.events = events;
    }

    public Game() {
        events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        if (events == null) {
            events = new ArrayList<>();
        }
        events.add(event);
        event.setGame(this);
    }

}
