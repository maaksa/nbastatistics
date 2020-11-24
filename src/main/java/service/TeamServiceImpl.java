package service;

import entity.Team;
import repositories.TeamRepository;

import java.util.List;

public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

}
