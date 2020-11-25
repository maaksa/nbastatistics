package loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Event;
import entity.Game;
import entity.Player;
import entity.Team;
import entity.enums.Type;
import jsonEntity.JsonEvent;
import jsonEntity.JsonPlayer;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Loader {
    private static Loader instance;

    public static Loader getInstance(){
        if (instance == null) instance = new Loader();
        return instance;
    }

    public void loadTeams(){

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Team>> typeRef = new TypeReference<List<Team>>(){};
        File file = new File("data\\teams.json");
        String path = file.getAbsolutePath();
        try {
            Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
            StringBuilder contentBuilder = new StringBuilder();
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
            List<Team> teams = mapper.readValue(contentBuilder.toString(), typeRef);

            for (Team t : teams) {


                // TODO dodati Team objekat u bazu preko servisa
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPlayers(){

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<JsonPlayer>> typeRef = new TypeReference<List<JsonPlayer>>(){};
        File file = new File("data\\players.json");
        String path = file.getAbsolutePath();
        try {
            Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
            StringBuilder contentBuilder = new StringBuilder();
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
            List<JsonPlayer> players = mapper.readValue(contentBuilder.toString(), typeRef);

            for (JsonPlayer p : players) {
                Player player = new Player();
                player.setId(p.getId());
                player.setFirstName(p.getFirstName());
                player.setLastName(p.getLastName());
                player.setNumber(p.getNumber());
                player.setHeight(p.getHeight());
                player.setAge(p.getAge());
                player.setPosition(p.getPosition());
                // TODO dodati Team objekat po teamId vrednosti
                // TODO dodati u bazu preko servisa
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEvents(){

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<JsonEvent>> typeRef = new TypeReference<List<JsonEvent>>(){};
        File file = new File("data\\events.json");
        String path = file.getAbsolutePath();
        try {
            Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
            StringBuilder contentBuilder = new StringBuilder();
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
            List<JsonEvent> events = mapper.readValue(contentBuilder.toString(), typeRef);

            for (JsonEvent jsonEvent : events) {
                Event event = new Event();
                event.setType(jsonEvent.getType());
                event.setValue(jsonEvent.getPayload().getValue());
                if (jsonEvent.getType().equals(Type.START)){
                    Game game = new Game();
                    game.setId(jsonEvent.getGame());
                    // game.setGuestId();       // TODO naci guest i host tim po id-jevima iz jsonEventa i dodati objekte
                    // game.setHostId();
                    // TODO dodati ovaj novi game objekat u bazu i kao atribut eventu
                }  else {
                    // TODO naci game objekat po id-ju i dodati eventu kao atribut
                }

                // TODO dodati Player atribut po playerId (ako postoji)
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
                    player.setPosition(entity.enums.Position.CENTER);

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
