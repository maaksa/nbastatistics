package controller;

import entity.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.PlayerService;

import java.util.List;

@Controller
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping("/list")
    public String listPlayer(Model theModel){
        List<Player> thePlayers = playerService.findAll();

        theModel.addAttribute("players", thePlayers);

        return "players/list-players";
    }

}
