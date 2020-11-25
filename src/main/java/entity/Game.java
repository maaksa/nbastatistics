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
    private long Id;

    @ManyToOne()
    @JoinColumn(name = "Id")
    private Team guestTeam;

    @ManyToOne()
    @JoinColumn(name = "Id")
    private Team hostTeam;

    @OneToMany(mappedBy = "game")
    private List<Event> events;

    public Game(Team guestTeam, Team hostTeam) {
        this.guestTeam = guestTeam;
        this.hostTeam = hostTeam;
    }

    public Game() {
    }

}
