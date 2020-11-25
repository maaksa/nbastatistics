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
@Table(name = "team")
public class Team {

    @Id
    private long id;

    private String name;

    private String city;

    @OneToMany(mappedBy = "hostTeam")
    private List<Game> gamesHost;

    @OneToMany(mappedBy = "guestTeam")
    private List<Game> gamesGuest;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
    private List<Player> players;

    public Team(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Team() {

    }


}
