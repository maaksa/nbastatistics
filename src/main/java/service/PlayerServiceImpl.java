package service;

import entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.PlayerRepository;

import java.util.List;
import java.util.Optional;

public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
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


}
