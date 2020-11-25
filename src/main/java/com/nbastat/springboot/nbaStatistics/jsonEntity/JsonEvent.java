package com.nbastat.springboot.nbaStatistics.jsonEntity;

import com.nbastat.springboot.nbaStatistics.entity.enums.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JsonEvent {

    private long game;

    private Type type;

    private Payload payload;

    public JsonEvent(long game, Type type, Payload payload) {
        this.game = game;
        this.type = type;
        this.payload = payload;
    }

    public JsonEvent() {}
}
