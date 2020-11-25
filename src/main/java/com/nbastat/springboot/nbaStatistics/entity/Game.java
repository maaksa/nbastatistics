package com.nbastat.springboot.nbaStatistics.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "game")
public class Game {

    @Id
    private long idGame;

    private boolean finished;

    @ManyToOne()
    //@JoinColumn(name = "id")
    private Team guestTeam;

    @ManyToOne()
    //@JoinColumn(name = "id")
    private Team hostTeam;

    private int hostPoints;

    private int guestPoints;

    @OneToMany(mappedBy = "game")
    private List<Event> events;

    public Game(Team guestTeam, Team hostTeam, int hostPoints, int guestPoints) {
        this.guestTeam = guestTeam;
        this.hostTeam = hostTeam;
        this.hostPoints = hostPoints;
        this.guestPoints = guestPoints;
    }

    public Game() {
    }

}
