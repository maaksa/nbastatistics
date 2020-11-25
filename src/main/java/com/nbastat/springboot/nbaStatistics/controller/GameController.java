package com.nbastat.springboot.nbaStatistics.controller;

import com.nbastat.springboot.nbaStatistics.entity.Game;
import com.nbastat.springboot.nbaStatistics.entity.Player;
import com.nbastat.springboot.nbaStatistics.entity.Team;
import com.sun.media.jfxmedia.events.PlayerTimeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nbastat.springboot.nbaStatistics.service.GameService;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/games")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/Query1")
    public String listGames(Model model) {
        List<Game> games = gameService.listGames();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Game game : games) {
            Map<String, Object> map = new HashMap<>();
            map.put("homeTeam", gameService.homeTeam(game.getIdGame()).getName());
            map.put("homePoints", gameService.pointsForHostTeam(game.getIdGame()));
            map.put("awayTeam", gameService.awayTeam(game.getIdGame()).getName());
            map.put("awayPoints", gameService.pointsForAwayTeam(game.getIdGame()));
            map.put("finished", game.isFinished());
            map.put("id", game.getIdGame());
            mapList.add(map);
        }
        model.addAttribute("games", mapList);
        return "games/list-games";
    }

    @RequestMapping(value = "/Query2/{id}", method = RequestMethod.GET)
    public String playerDetails(@PathVariable("id") long id, Model model) {
        List<Player> players = new ArrayList<>();
        List<Player> allPlayers = null;

        Team hostTeam = gameService.homeTeam(id);
        Team guestTeam = gameService.awayTeam(id);

        allPlayers = hostTeam.getPlayers();
        allPlayers.addAll(guestTeam.getPlayers());

        for (Player player : allPlayers) {
            Map<String, Object> map = new HashMap<>();
            map.put("playerName", player.getFirstName() + " " + player.getLastName());
            map.put("points", gameService.totalPointsForPlayer(id, player.getIdPlayer()));
            /*map.put("awayTeam", gameService.awayTeam(game.getIdGame()).getName());
            map.put("awayPoints", gameService.pointsForAwayTeam(game.getIdGame()));
            map.put("finished", game.isFinished());
            map.put("id", game.getIdGame());
            mapList.add(map);*/
        }

        model.addAttribute("players", allPlayers);

        return "games/list-players";
    }

}
