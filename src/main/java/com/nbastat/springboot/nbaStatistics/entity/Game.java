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

    @ManyToOne()
    //@JoinColumn(name = "id")
    private Team guestTeam;

    @ManyToOne()
    @JoinColumn(name = "id")
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
