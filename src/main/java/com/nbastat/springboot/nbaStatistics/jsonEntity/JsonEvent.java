package com.nbastat.springboot.nbaStatistics.jsonEntity;

import com.nbastat.springboot.nbaStatistics.entity.enums.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Getter
@ToString
public class JsonEvent {

    private long game;

    private Type type;

    private Map<String, Integer> payload;

    public JsonEvent(long game, Type type, Map<String, Integer> payload) {
        this.game = game;
        this.type = type;
        this.payload = payload;
    }

    public JsonEvent() {}
}
