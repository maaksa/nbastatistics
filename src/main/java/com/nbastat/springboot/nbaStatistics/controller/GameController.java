package com.nbastat.springboot.nbaStatistics.controller;

import com.nbastat.springboot.nbaStatistics.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nbastat.springboot.nbaStatistics.service.GameService;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/Query1")
    public String listGames(Model model){
        List<Game> games = gameService.listGames();
        return null;
    }

}
