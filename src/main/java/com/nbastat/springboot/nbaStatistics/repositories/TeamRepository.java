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

    @Query("select distinct t from Team t left join t.gamesGuest gg left join t.gamesHost gh " +
            "group by t.id order by t.wins/(count(gg.finished) + count(gh.finished)) desc, " +
            "sum(case when gg.finished = true then gg.guestPoints else 0 end) - sum(case when gg.finished = true then gg.hostPoints else 0 end) + " +
            "sum(case when gh.finished = true then gh.hostPoints else 0 end) - sum(case when gh.finished = true then gh.guestPoints else 0 end) desc")
    List<Team> sortAndList();

}
