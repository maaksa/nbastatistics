package com.nbastat.springboot.nbaStatistics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbastat.springboot.nbaStatistics.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nbastat.springboot.nbaStatistics.service.TeamService;

import java.util.List;

@Controller
@RequestMapping("/teams")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/list")
    public String listTeam(Model theModel) {
        List<Team> theTeams = teamService.findAll();

        theModel.addAttribute("teams", theTeams);

        return "teams/list-teams";
    }

    @GetMapping("/Query6")
    public ResponseEntity<Object> sortByWinningPercentage(Model model){
        try {
            List<Team> teams = teamService.sortByWinningPercentage();
            ObjectMapper objectMapper = new ObjectMapper();
            String string = objectMapper.writeValueAsString(teams);
            return new ResponseEntity<Object>(string, HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
