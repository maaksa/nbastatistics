package com.nbastat.springboot.nbaStatistics.service;

import com.nbastat.springboot.nbaStatistics.entity.Game;
import com.nbastat.springboot.nbaStatistics.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import com.nbastat.springboot.nbaStatistics.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public int pointsForHostTeam(int id) {
        return gameRepository.pointsForHostTeam(id);
    }

    @Override
    public int pointsForAwayTeam(int id) {
        return gameRepository.pointsForAwayTeam(id);
    }

    @Override
    public Team homeTeam(int id) {
        return gameRepository.homeTeam(id);
    }

    @Override
    public Team awayTeam(int id) {
        return gameRepository.awayTeam(id);
    }
}
