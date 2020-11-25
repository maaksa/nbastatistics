package com.nbastat.springboot.nbaStatistics.repositories;

import com.nbastat.springboot.nbaStatistics.entity.Team;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from Team t where t.id = :id")
    Team findById(long id);

    @Query("select distinct t from Team t left join t.gamesGuest gg left join t.gamesHost gh group by t.id order by t.wins/(t.gamesGuest.size + t.gamesHost.size) desc, " +
            "sum(gg.guestPoints) - sum(gg.hostPoints) + sum(gh.hostPoints) - sum(gh.guestPoints) desc")
    List<Team> sortAndList();

}
