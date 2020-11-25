package com.nbastat.springboot.nbaStatistics.service;

import com.nbastat.springboot.nbaStatistics.entity.Game;
import com.nbastat.springboot.nbaStatistics.entity.Player;
import com.nbastat.springboot.nbaStatistics.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import com.nbastat.springboot.nbaStatistics.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Autowired
    GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> listGames() {
        return gameRepository.listGames();
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }

    @Override
    public Game findById(long theId) {
        Optional<Game> result = gameRepository.findById(theId);

        Game theGame = null;

        if (result.isPresent()) {
            theGame = result.get();
        } else {
            throw new RuntimeException("Did not find player id -" + theId);
        }

        return theGame;
    }

    @Override
    public int pointsForHostTeam(long id) {
        return gameRepository.pointsForHostTeam(id);
    }

    @Override
    public int pointsForAwayTeam(long id) {
        return gameRepository.pointsForAwayTeam(id);
    }

    @Override
    public Team homeTeam(long id) {
        return gameRepository.homeTeam(id);
    }

    @Override
    public Team awayTeam(long id) {
        return gameRepository.awayTeam(id);
    }
}
