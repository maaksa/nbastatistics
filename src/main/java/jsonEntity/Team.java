package jsonEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class Team {

    private long id;

    private String name;

    private String city;

    public Team(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Team(){}

}
