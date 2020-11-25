package com.nbastat.springboot.nbaStatistics.repositories;

import com.nbastat.springboot.nbaStatistics.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select sum(e.value) from Player p join p.events e where p.idPlayer = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.POINT")
    int totalPoints(long id);

    @Query("select avg(e.value) from Player p join p.events e where p.idPlayer = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.POINT")
    double averagePoints(long id);

    @Query("select avg(e.value) from Player p join p.events e where p.idPlayer = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.ASSIST")
    double averageAssists(long id);

    @Query("select avg(e.value) from Player p join p.events e where p.idPlayer = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.JUMP")
    double averageRebounds(long id);

    @Query("select p from Player p where p.team.id = :id")
    List<Player> findAllbyTeam(long id);

}
