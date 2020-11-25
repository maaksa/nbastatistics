package com.nbastat.springboot.nbaStatistics.controller;

import com.nbastat.springboot.nbaStatistics.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nbastat.springboot.nbaStatistics.service.GameService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Game game : games) {
            Map<String, Object> map = new HashMap<>();
            map.put("homeTeam", gameService.homeTeam(game.getIdGame()).getName());
            map.put("homePoints", gameService.pointsForHostTeam(game.getIdGame()));
            map.put("awayTeam", gameService.awayTeam(game.getIdGame()).getName());
            map.put("awayPoints", gameService.pointsForAwayTeam(game.getIdGame()));
            mapList.add(map);
        }
        model.addAttribute("games", mapList);
        return "games/list-games";
    }

}
