package com.nbastat.springboot.nbaStatistics.service;

import com.nbastat.springboot.nbaStatistics.entity.Game;
import com.nbastat.springboot.nbaStatistics.entity.Player;
import com.nbastat.springboot.nbaStatistics.entity.Team;

import java.util.List;

public interface GameService {

    List<Game> listGames();

    void save(Game game);

    Game findById(long id);

    int pointsForHostTeam(long id);

    int pointsForAwayTeam(long id);

    Team homeTeam(long id);

    Team awayTeam(long id);

    int totalPointsForPlayer(long gameId, long playerId);

}
