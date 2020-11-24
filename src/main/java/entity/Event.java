package entity;

import entity.enums.Type;
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
    private long Id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "id")
    private Player player;

    private int value;

    @ManyToOne
    @JoinColumn(name = "Id")
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
