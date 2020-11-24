package service;

import entity.Player;

import java.util.List;

public interface PlayerService {

    public List<Player> findAll();

    public Player findById(long theId);

}
