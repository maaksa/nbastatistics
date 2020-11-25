package com.nbastat.springboot.nbaStatistics.jsonEntity;

import com.nbastat.springboot.nbaStatistics.entity.enums.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JsonPlayerMinimal {

    private long id;

    private String firstName;

    private String lastName;

    public JsonPlayerMinimal() {}


}
