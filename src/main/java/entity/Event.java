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

    private long game;

    private Type type;

    private Payload payload;

    public Event(long game, Type type, Payload payload) {
        this.game = game;
        this.type = type;
        this.payload = payload;
    }
}
