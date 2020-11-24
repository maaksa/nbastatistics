package service;

import entity.Game;
import entity.Team;

import java.util.List;

public interface GameService {

    List<Game> listGames();

    int pointsForHostTeam(int id);

    int pointsForAwayTeam(int id);

    Team homeTeam(int id);

    Team awayTeam(int id);

}
