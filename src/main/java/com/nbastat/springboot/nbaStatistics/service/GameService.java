package com.nbastat.springboot.nbaStatistics.service;

import com.nbastat.springboot.nbaStatistics.entity.Game;
import com.nbastat.springboot.nbaStatistics.entity.Team;

import java.util.List;

public interface GameService {

    List<Game> listGames();

    int pointsForHostTeam(int id);

    int pointsForAwayTeam(int id);

    Team homeTeam(int id);

    Team awayTeam(int id);

}
