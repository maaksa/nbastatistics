package com.nbastat.springboot.nbaStatistics.jsonEntity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Payload {

    private long hostId;

    private long guestId;

    private long playerId;

    private int value;

    public Payload(long hostId, long guestId, long playerId, int value) {
        this.hostId = hostId;
        this.guestId = guestId;
        this.playerId = playerId;
        this.value = value;
    }
}
