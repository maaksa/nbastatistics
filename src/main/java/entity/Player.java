package entity;

import entity.enums.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    //manyToOne?
    private long teamId;

    private String name;

    private String surname;

    private int number;

    private int height;

    private int age;

    private Position position;

    public Player(long teamId, String name, String surname, int number, int height, int age, Position position) {
        this.teamId = teamId;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.height = height;
        this.age = age;
        this.position = position;
    }
}
