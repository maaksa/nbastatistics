package com.nbastat.springboot.nbaStatistics.service;

import com.nbastat.springboot.nbaStatistics.entity.Player;
import com.nbastat.springboot.nbaStatistics.jsonEntity.JsonPlayer;
import com.nbastat.springboot.nbaStatistics.jsonEntity.JsonPlayerMinimal;
import org.springframework.beans.factory.annotation.Autowired;
import com.nbastat.springboot.nbaStatistics.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<JsonPlayerMinimal> doubledouble() {
        List<Player> allPlayers = findAll();
        HashMap<Player, Integer> doubles = new HashMap<>();
        for (Player p: allPlayers){
            int points = totalPoints(p.getIdPlayer());
            int jumps = totalJumps(p.getIdPlayer());
            int assists = totalAssists(p.getIdPlayer());
            int maxDouble = 0;
            if (points > 9 && jumps > 9){
                if (points+jumps > maxDouble)
                    maxDouble = points+jumps;
            }
            if (points > 9 && assists > 9){
                if (points+assists > maxDouble)
                    maxDouble = points+assists;
            }
            if (jumps > 9 && assists > 9){
                if (jumps+assists > maxDouble)
                    maxDouble = jumps+assists;
            }
            doubles.put(p, maxDouble);
        }
        Stream<Map.Entry<Player,Integer>> sorted = doubles.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
        Map top5 = sorted.limit(5).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        List<JsonPlayerMinimal> result = new ArrayList<>();
        for (Object o: top5.keySet()){
            Player p = (Player)o;
            JsonPlayerMinimal jp = new JsonPlayerMinimal();
            jp.setFirstName(p.getFirstName());
            jp.setLastName(p.getLastName());
            jp.setId(p.getIdPlayer());
            result.add(jp);
        }

        return result;
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> findAllbyTeam(long id) {
        return playerRepository.findAllbyTeam(id);
    }

    @Override
    public Player findById(long theId) {
        Optional<Player> result = playerRepository.findById(theId);

        Player thePlayer = null;

        if (result.isPresent()) {
            thePlayer = result.get();
        } else {
            throw new RuntimeException("Did not find player id -" + theId);
        }

        return thePlayer;
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public int totalPoints(long id) {
        return playerRepository.totalPoints(id);
    }

    @Override
    public int totalJumps(long id) {
        return playerRepository.totalJumps(id);
    }

    @Override
    public int totalAssists(long id) {
        return playerRepository.totalAssists(id);
    }

    @Override
    public double averagePoints(long id) {
        return playerRepository.averagePoints(id);
    }

    @Override
    public double averageAssists(long id) {
        return playerRepository.averageAssists(id);
    }

    @Override
    public double averageRebounds(long id) {
        return playerRepository.averageRebounds(id);
    }

    @Override
    public List<Player> maxPoints() {
        return playerRepository.maxPoints();
    }


}
