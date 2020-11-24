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

    @ManyToOne()
    @JoinColumn(name = "Id")
    private Team guestId;

    @ManyToOne()
    @JoinColumn(name = "Id")
    private Team hostId;

    @OneToMany(mappedBy = "game")
    private List<Event> events;

    public Game(Team guestId, Team hostId) {
        this.guestId = guestId;
        this.hostId = hostId;
    }

    public Game() {
    }

}
