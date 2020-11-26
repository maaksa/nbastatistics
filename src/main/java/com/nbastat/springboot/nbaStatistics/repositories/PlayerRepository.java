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

    @Query("select sum(e.value) from Player p join p.events e where p.idPlayer = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.ASSIST")
    int totalAssists(long id);

    @Query("select sum(e.value) from Player p join p.events e where p.idPlayer = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.JUMP")
    int totalJumps(long id);

    @Query("select avg(e.value) from Player p join p.events e where p.idPlayer = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.POINT")
    double averagePoints(long id);

    @Query("select avg(e.value) from Player p join p.events e where p.idPlayer = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.ASSIST")
    double averageAssists(long id);

    @Query("select avg(e.value) from Player p join p.events e where p.idPlayer = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.JUMP")
    double averageRebounds(long id);

    @Query("select p from Player p where p.team.id = :id")
    List<Player> findAllbyTeam(long id);

    @Query("select sum(e.value) from Player p join p.events e where e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.POINT group by p.idPlayer order by sum(e.value) desc")
    List<Integer> maxPoints();

    @Query("select sum(e.value) from Player p join p.events e where e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.ASSIST group by p.idPlayer order by sum(e.value) desc")
    List<Integer> maxAssists();

    @Query("select sum(e.value) from Player p join p.events e where e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.JUMP group by p.idPlayer order by sum(e.value) desc")
    List<Integer> maxJumps();

    @Query("select p from Player p join p.events e where e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.POINT group by p.idPlayer having sum(e.value) = :max")
    List<Player> maxPointsPlayer(long max);

    @Query("select p from Player p join p.events e where e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.ASSIST group by p.idPlayer having sum(e.value) = :max")
    List<Player> maxAssistsPlayer(long max);

    @Query("select p from Player p join p.events e where e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.JUMP group by p.idPlayer having sum(e.value) = :max")
    List<Player> maxJumpsPlayer(long max);

}
