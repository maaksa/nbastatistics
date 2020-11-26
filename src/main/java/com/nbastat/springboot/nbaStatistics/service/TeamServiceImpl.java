package com.nbastat.springboot.nbaStatistics.service;

import com.nbastat.springboot.nbaStatistics.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nbastat.springboot.nbaStatistics.repositories.TeamRepository;

import java.util.List;

@Service("teamService")
@Transactional
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team findById(long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> saveAll(List<Team> teams) {
        return teamRepository.saveAll(teams);
    }

    @Override
    public List<Team> sortByWinningPercentage() {
        return teamRepository.sortAndList();
    }

}
