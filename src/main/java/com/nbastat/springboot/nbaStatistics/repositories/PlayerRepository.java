package com.nbastat.springboot.nbaStatistics.repositories;

import com.nbastat.springboot.nbaStatistics.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select sum(e.value) from Player p join fetch Event e where e.player.id = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.POINT")
    int totalPoints(long id);

    @Query("select avg(e.value) from Player p join fetch Event e where e.player.id = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.POINT")
    double averagePoints(long id);

    @Query("select avg(e.value) from Player p join fetch Event e where e.player.id = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.ASSIST")
    double averageAssists(long id);

    @Query("select avg(e.value) from Player p join fetch Event e where e.player.id = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.JUMP")
    double averageRebounds(long id);

}
