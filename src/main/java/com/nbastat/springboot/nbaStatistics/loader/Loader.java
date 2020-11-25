package com.nbastat.springboot.nbaStatistics.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbastat.springboot.nbaStatistics.entity.Event;
import com.nbastat.springboot.nbaStatistics.entity.Game;
import com.nbastat.springboot.nbaStatistics.entity.Player;
import com.nbastat.springboot.nbaStatistics.entity.Team;
import com.nbastat.springboot.nbaStatistics.entity.enums.Type;
import com.nbastat.springboot.nbaStatistics.jsonEntity.JsonEvent;
import com.nbastat.springboot.nbaStatistics.jsonEntity.JsonPlayer;
import com.nbastat.springboot.nbaStatistics.service.EventService;
import com.nbastat.springboot.nbaStatistics.service.GameService;
import com.nbastat.springboot.nbaStatistics.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nbastat.springboot.nbaStatistics.service.TeamService;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Component("loader")
public class Loader {

    private GameService gameService;
    private PlayerService playerService;
    private TeamService teamService;
    private EventService eventService;

    @Autowired
    public Loader(EventService eventService, TeamService teamService, GameService gameService, PlayerService playerService) {
        this.teamService = teamService;
        this.eventService = eventService;
        this.playerService = playerService;
        this.gameService = gameService;
    }

    public void loadTeams() {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Team>> typeRef = new TypeReference<List<Team>>() {
        };
        File file = new File("data\\teams.json");
        String path = file.getAbsolutePath();
        try {
            Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
            StringBuilder contentBuilder = new StringBuilder();
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
            List<Team> teams = mapper.readValue(contentBuilder.toString(), typeRef);

            for (Team t : teams) {
                System.out.println(t.getId());
                teamService.save(t);
                // TODO dodati Team objekat u bazu preko servisa
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPlayers() {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<JsonPlayer>> typeRef = new TypeReference<List<JsonPlayer>>() {
        };
        File file = new File("data\\players.json");
        String path = file.getAbsolutePath();
        try {
            Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
            StringBuilder contentBuilder = new StringBuilder();
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
            List<JsonPlayer> players = mapper.readValue(contentBuilder.toString(), typeRef);

            for (JsonPlayer p : players) {
                Player player = new Player();
                player.setIdPlayer(p.getId());
                player.setFirstName(p.getFirstName());
                player.setLastName(p.getLastName());
                player.setNumber(p.getNumber());
                player.setHeight(p.getHeight());
                player.setAge(p.getAge());
                player.setPosition(p.getPosition());//provera

                Team t = teamService.findById(p.getTeamId());
                player.setTeam(t);

                playerService.save(player);
                System.out.println(player.getFirstName());
                // TODO dodati Team objekat po teamId vrednosti
                // TODO dodati u bazu preko servisa
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEvents() {


        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<JsonEvent>> typeRef = new TypeReference<List<JsonEvent>>() {
        };
        File file = new File("data\\events_full.json");
        String path = file.getAbsolutePath();
        try {
            Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
            StringBuilder contentBuilder = new StringBuilder();
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
            List<JsonEvent> events = mapper.readValue(contentBuilder.toString(), typeRef);

            for (JsonEvent jsonEvent : events) {
                System.out.println(jsonEvent);
                Event event = new Event();
                event.setType(jsonEvent.getType());
                System.out.println(jsonEvent.getType());
                if (jsonEvent.getPayload().get("value") != null)
                    event.setValue(jsonEvent.getPayload().get("value"));
                if (jsonEvent.getType().equals(Type.START)) {
                    Game game = new Game();
                    game.setIdGame(jsonEvent.getGame());
                    Team tHost = teamService.findById(jsonEvent.getPayload().get("hostId"));
                    Team tGuest = teamService.findById(jsonEvent.getPayload().get("guestId"));
                    game.setHostTeam(tHost);
                    game.setGuestTeam(tGuest);
                    gameService.save(game);
                    event.setGame(game);
                    eventService.save(event);
                    // game.setGuestId();       // TODO naci guest i host tim po id-jevima iz payload mape i dodati objekte
                    // game.setHostId();
                    // TODO dodati ovaj novi game objekat u bazu i kao atribut eventu
                } else {
                    Game game = gameService.findById(jsonEvent.getGame());
                    if (jsonEvent.getPayload().get("playerId") != null) {
                        Player player = playerService.findById(jsonEvent.getPayload().get("playerId"));
                        event.setPlayer(player);
                    }
                    event.setGame(game);
                    eventService.save(event);
                    // TODO naci game objekat po id-ju i dodati eventu kao atribut
                }

                // TODO dodati Player atribut po playerId iz payload mape (ako postoji)
                // TODO dodati Event u bazu preko servisa
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }









/*
    public void loadPlayers1(){

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Object>> typeRef = new TypeReference<List<Object>>(){};
        File file = new File("data\\players.json");
        String path = file.getAbsolutePath();
        try {
            Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
            StringBuilder contentBuilder = new StringBuilder();
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
            List<Object> objects = mapper.readValue(contentBuilder.toString(), typeRef);

            for (Object o : objects) {
                Player player = new Player();
                LinkedHashMap jsonPlayer = (LinkedHashMap)o;

                player.setId(Integer.toUnsignedLong((int)jsonPlayer.get("id")));
                player.setFirstName((String)jsonPlayer.get("firstName"));
                player.setLastName((String)jsonPlayer.get("lastName"));
                player.setNumber((int)jsonPlayer.get("number"));
                player.setHeight((int)jsonPlayer.get("height"));
                player.setAge((int)jsonPlayer.get("age"));
                if (jsonPlayer.get("position").toString().equals(Position.CENTER.toString()))
                    player.setPosition(com.nbastat.springboot.nbaStatistics.entity.enums.Position.CENTER);

               // player.setPosition(jsonPlayer.get("position")); // TODO sta sa enumom?
                // TODO dodati Team objekat po jsonPlayer.get("teamId") vrednosti
                // TODO dodati player u bazu preko servisa
                System.out.println(player);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
}
