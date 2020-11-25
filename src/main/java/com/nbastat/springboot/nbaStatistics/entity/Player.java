package com.nbastat.springboot.nbaStatistics.entity;

import com.nbastat.springboot.nbaStatistics.entity.enums.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "player")
public class Player {

    @Id
    private long idPlayer;

    @ManyToOne()
    @JoinColumn(name = "id")
    private Team team;

    @OneToMany(mappedBy = "player")
    private List<Event> events;

    private String firstName;

    private String lastName;

    private int number;

    private int height;

    private int age;

    private Position position;

    public Player(long idPlayer, Team team, String firstName, String lastName, int number, int height, int age, Position position) {
        this.idPlayer = idPlayer;
        this.team = team;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.height = height;
        this.age = age;
        this.position = position;
    }

    public Player() {
    }


}
