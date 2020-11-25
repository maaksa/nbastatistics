package com.nbastat.springboot.nbaStatistics.repositories;

import com.nbastat.springboot.nbaStatistics.entity.Game;
import com.nbastat.springboot.nbaStatistics.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select g from Game g")
    List<Game> listGames();

    @Query("select sum(e.value) from Game g join fetch Event e where g.Id = :id and e.player.team.id = g.hostTeam.id")
    int pointsForHostTeam(int id);

    @Query("select sum(e.value) from Game g join fetch Event e where g.Id = :id and e.player.team.id = g.guestTeam.id")
    int pointsForAwayTeam(int id);

    @Query("select g.hostTeam from Game g where g.Id =: id")
    Team homeTeam(int id);

    @Query("select g.guestTeam from Game g where g.Id = :id")
    Team awayTeam(int id);
}
