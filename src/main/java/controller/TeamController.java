package controller;

import entity.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.TeamService;

import java.util.List;

@Controller
@RequestMapping("/teams")
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/list")
    public String listTeam(Model theModel) {
        List<Team> theTeams = teamService.findAll();

        theModel.addAttribute("teams", theTeams);

        return "teams/list-teams";
    }

}
