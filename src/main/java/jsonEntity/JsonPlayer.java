package jsonEntity;

import entity.enums.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
public class JsonPlayer {

    private long id;

    private long teamId;

    private String firstName;

    private String lastName;

    private int number;

    private int height;

    private int age;

    private Position position;

    public JsonPlayer() {}


}
