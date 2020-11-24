package service;

import entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> findAll();

    Player findById(long id);

    int totalPoints(long id);

    double averagePoints(long id);

    double averageAssists(long id);

    double averageRebounds(long id);

}
