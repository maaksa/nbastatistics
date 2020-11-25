package controller;

import entity.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.GameService;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/Query1")
    public String listGames(Model model){
        List<Game> games = gameService.listGames();
        return null;
    }

}
