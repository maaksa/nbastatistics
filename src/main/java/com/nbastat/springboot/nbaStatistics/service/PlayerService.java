package com.nbastat.springboot.nbaStatistics.service;

import com.nbastat.springboot.nbaStatistics.entity.Player;
import com.nbastat.springboot.nbaStatistics.jsonEntity.JsonPlayer;
import com.nbastat.springboot.nbaStatistics.jsonEntity.JsonPlayerMinimal;

import java.util.List;

public interface PlayerService {

    List<JsonPlayerMinimal> doubledouble();

    List<Player> findAll();

    List<Player> findAllbyTeam(long id);

    Player findById(long id);

    void save(Player player);

    int totalPoints(long id);

    int totalJumps(long id);

    int totalAssists(long id);

    double averagePoints(long id);

    double averageAssists(long id);

    double averageRebounds(long id);

    List<Integer> maxPoints();

    List<Integer> maxAssists();

    List<Integer> maxJumps();

    List<Player> maxPointsPlayer(long max);

    List<Player> maxAssistsPlayer(long max);

    List<Player> maxJumpsPlayer(long max);


}
