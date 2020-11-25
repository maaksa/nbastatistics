package com.nbastat.springboot.nbaStatistics.service;

import com.nbastat.springboot.nbaStatistics.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> findAll();

    Player findById(long id);

    void save(Player player);

    int totalPoints(long id);

    double averagePoints(long id);

    double averageAssists(long id);

    double averageRebounds(long id);

}
