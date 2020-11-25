package com.nbastat.springboot.nbaStatistics.service;

import com.nbastat.springboot.nbaStatistics.entity.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAll();

    Team findById(long id);

    Team save(Team team);

    List<Team> saveAll(List<Team> teams);

    List<Team> sortByWinningPercentage();

}
