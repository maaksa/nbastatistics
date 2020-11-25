package com.nbastat.springboot.nbaStatistics.service;

import com.nbastat.springboot.nbaStatistics.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import com.nbastat.springboot.nbaStatistics.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(long theId) {
        Optional<Player> result = playerRepository.findById(theId);

        Player thePlayer = null;

        if (result.isPresent()) {
            thePlayer = result.get();
        } else {
            throw new RuntimeException("Did not find player id -" + theId);
        }

        return thePlayer;
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public int totalPoints(long id) {
        return playerRepository.totalPoints(id);
    }

    @Override
    public double averagePoints(long id) {
        return playerRepository.averagePoints(id);
    }

    @Override
    public double averageAssists(long id) {
        return playerRepository.averageAssists(id);
    }

    @Override
    public double averageRebounds(long id) {
        return playerRepository.averageRebounds(id);
    }


}
