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
                teamService.save(t);
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
                player.setPosition(p.getPosition());

                Team t = teamService.findById(p.getTeamId());
                player.setTeam(t);

                playerService.save(player);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEvents() {

        List<Player> playersHost = null;
        List<Player> playersGuest = null;
        boolean assisted = false;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<JsonEvent>> typeRef = new TypeReference<List<JsonEvent>>() {
        };
        File file = new File("data\\events_full_correct.json");
        String path = file.getAbsolutePath();
        try {
            Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
            StringBuilder contentBuilder = new StringBuilder();
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
            List<JsonEvent> events = mapper.readValue(contentBuilder.toString(), typeRef);

            for (int i = 0; i < events.size(); i++) {
                JsonEvent jsonEvent = events.get(i);
                Event event = new Event();
                event.setType(jsonEvent.getType());
                if (jsonEvent.getPayload().get("value") != null) {
                    if (jsonEvent.getType().equals(Type.ASSIST) && jsonEvent.getPayload().get("value") == 1) {
                        JsonEvent tempJsonEvent = events.get(i + 1);
                        if (tempJsonEvent.getType().equals(Type.POINT)) {
                            if (tempJsonEvent.getPayload().get("value") == 2 ||
                                    tempJsonEvent.getPayload().get("value") == 3) {
                                event.setValue(jsonEvent.getPayload().get("value"));
                            } else {
                                System.out.println("Invalid event");
                                System.out.println(jsonEvent);
                                continue;
                            }
                            assisted = true;
                        }
                    } else if (jsonEvent.getType().equals(Type.POINT)) {
                        if ((jsonEvent.getPayload().get("value") == 1 && !assisted) ||
                                jsonEvent.getPayload().get("value") == 2 ||
                                jsonEvent.getPayload().get("value") == 3) {
                            assisted = false;
                            event.setValue(jsonEvent.getPayload().get("value"));
                        } else {
                            System.out.println("Invalid event");
                            System.out.println(jsonEvent);
                            continue;
                        }
                    } else if (jsonEvent.getType().equals(Type.JUMP) && jsonEvent.getPayload().get("value") == 1) {
                        event.setValue(jsonEvent.getPayload().get("value"));
                    }
                }
                if (jsonEvent.getType().equals(Type.START)) {
                    Game game = new Game();
                    game.setIdGame(jsonEvent.getGame());
                    game.setFinished(false);
                    Team tHost = teamService.findById(jsonEvent.getPayload().get("hostId"));
                    Team tGuest = teamService.findById(jsonEvent.getPayload().get("guestId"));

                    playersHost = playerService.findAllbyTeam(tHost.getId());
                    playersGuest = playerService.findAllbyTeam(tGuest.getId());

                    game.setHostTeam(tHost);
                    game.setGuestTeam(tGuest);
                    gameService.save(game);
                    event.setGame(game);
                    eventService.save(event);
                } else if (jsonEvent.getType().equals(Type.END)) {
                    Game game = gameService.findById(jsonEvent.getGame());
                    Team team;
                    if (game.getHostPoints() > game.getGuestPoints()) {
                        team = game.getHostTeam();
                    } else {
                        team = game.getGuestTeam();
                    }
                    team.setWins(team.getWins() + 1);
                    game.setFinished(true);
                    event.setGame(game);
                    eventService.save(event);
                    gameService.save(game);
                    teamService.save(team);
                } else {
                    Game game = gameService.findById(jsonEvent.getGame());
                    if (game.isFinished()) {
                        System.out.println("Invalid event - game already ended\n");
                        System.out.println(jsonEvent);
                        continue;
                    }

                    Team tHost = game.getHostTeam();
                    Team tGuest = game.getGuestTeam();
                    boolean isGuest = false;

                    if (jsonEvent.getPayload().get("playerId") != null) {
                        Player player = playerService.findById(jsonEvent.getPayload().get("playerId"));
                        if (playersHost != null && playersGuest != null) {
                            for (Player p : tHost.getPlayers()) {
                                if (player.getIdPlayer() == p.getIdPlayer()) {
                                    event.setPlayer(player);
                                    isGuest = false;
                                    break;
                                }
                            }
                            for (Player p : tGuest.getPlayers()) {
                                if (player.getIdPlayer() == p.getIdPlayer()) {
                                    event.setPlayer(player);
                                    isGuest = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (event.getType().equals(Type.POINT)) {
                        if (isGuest) {
                            game.setGuestPoints(game.getGuestPoints() + event.getValue());
                        } else {
                            game.setHostPoints(game.getHostPoints() + event.getValue());
                        }
                    }
                    event.setGame(game);
                    eventService.save(event);
                    gameService.save(game);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
