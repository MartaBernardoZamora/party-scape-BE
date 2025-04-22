package dev.marta_bernardo.party_escape.game;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("${api-base-path}/games")
public class GameController {
    GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping()
    public ResponseEntity<List<GameResponseDTO>> index() {
        return ResponseEntity.ok(gameService.getAll());
    }
    

}
