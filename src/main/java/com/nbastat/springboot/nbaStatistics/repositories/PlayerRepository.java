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

    /* Prikazati igrače koji su postigli najviše poena, skokova i asistencija na nivou svih
        utakmica (jedan igrač po kategoriji, ukoliko je više igrača postiglo isti maksimalni broj prikazati ih sve)*/
    @Query("select p from Player p join p.events e group by p " +
            "having sum(case when (e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.POINT) then e.value else 0 end) in " +
            "(select sum(e.value) from Player p join p.events e where e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.POINT group by p)")
    List<Player> maxPoints();

    @Query("select max(sum(e.value)) from Player p join p.events e where p.idPlayer = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.ASSIST")
    List<Player> maxAssists();

    @Query("select max(sum(e.value)) from Player p join p.events e where p.idPlayer = :id and e.type = com.nbastat.springboot.nbaStatistics.entity.enums.Type.JUMP")
    List<Player> maxJumps();



}
