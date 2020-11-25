package com.nbastat.springboot.nbaStatistics.entity;

import com.nbastat.springboot.nbaStatistics.entity.enums.Type;
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
    private long idEvent;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String typeString;

    @ManyToOne()
    //@JoinColumn(name = "idPlayer")
    private Player player;

    private int value;

    @ManyToOne()
    //@JoinColumn(name = "idGame")
    private Game game;

    public Event(Type type, Player player, int value, Game game) {
        this.type = type;
        this.player = player;
        this.value = value;
        this.game = game;
    }

    public Event() {
    }


}
