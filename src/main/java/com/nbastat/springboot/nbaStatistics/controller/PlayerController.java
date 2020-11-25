package com.nbastat.springboot.nbaStatistics.controller;

import com.nbastat.springboot.nbaStatistics.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nbastat.springboot.nbaStatistics.service.PlayerService;

import java.util.List;

@Controller
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public String listPlayer(Model theModel){
        List<Player> thePlayers = playerService.findAll();

        theModel.addAttribute("players", thePlayers);

        return "players/list-players";
    }

    @RequestMapping (value = "/Query3/{id}", method = RequestMethod.GET)
    public String statPlayer(@PathVariable("id") long id, Model model){

        model.addAttribute("totalPoints", playerService.totalPoints(id));
        model.addAttribute("averagePoints", playerService.averagePoints(id));
        model.addAttribute("totalAssists", playerService.averageAssists(id));
        model.addAttribute("averageRebounds", playerService.averageRebounds(id));

        //todo treba da vrati json objekat -> hash map je ok
        return "players/stat-player";
    }

}
