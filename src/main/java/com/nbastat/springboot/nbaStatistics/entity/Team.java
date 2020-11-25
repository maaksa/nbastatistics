package com.nbastat.springboot.nbaStatistics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private double wins;

    @OneToMany(mappedBy = "hostTeam")
    @JsonIgnore
    private List<Game> gamesHost;

    @OneToMany(mappedBy = "guestTeam")
    @JsonIgnore
    private List<Game> gamesGuest;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
    @JsonIgnore
    private List<Player> players;

    public Team(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Team() {

    }


}
