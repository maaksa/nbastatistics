package com.nbastat.springboot.nbaStatistics.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.nbastat.springboot.nbaStatistics.entity.Player;
import com.nbastat.springboot.nbaStatistics.jsonEntity.JsonPlayer;
import com.nbastat.springboot.nbaStatistics.jsonEntity.JsonPlayerMinimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nbastat.springboot.nbaStatistics.service.PlayerService;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public String listPlayer(Model theModel) {
        List<Player> thePlayers = playerService.findAll();

        theModel.addAttribute("players", thePlayers);

        return "players/list-players";
    }

    @RequestMapping(value = "/Query3/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> statPlayer(@PathVariable("id") long id, Model model) {
        try {
            Player p = playerService.findById(id);
            ObjectMapper mapper = new ObjectMapper();

            ObjectNode player = mapper.createObjectNode();

            player.put("id", id);
            player.put("firstName", p.getFirstName());
            player.put("lastName", p.getLastName());
            player.put("totalPoints", playerService.totalPoints(id));
            player.put("averagePoints", playerService.averagePoints(id));
            player.put("totalAssists", playerService.averageAssists(id));
            player.put("averageRebounds", playerService.averageRebounds(id));

            return new ResponseEntity<Object>(player, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/Query4", method = RequestMethod.GET)
    public ResponseEntity<Object> statPlayer(Model model) {
        try {
            List<Player> players = playerService.maxPoints();

            System.out.println(players);

            ObjectMapper objectMapper = new ObjectMapper();
            String string = objectMapper.writeValueAsString(players);

            return new ResponseEntity<Object>(string, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Query5")
    public ResponseEntity<Object> doubles(Model model) {
        try {
            List<JsonPlayerMinimal> players = playerService.doubledouble();

            ObjectMapper objectMapper = new ObjectMapper();
            String string = objectMapper.writeValueAsString(players);
            System.out.println(string);

            return new ResponseEntity<Object>(string, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


}
