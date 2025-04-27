package dev.marta_bernardo.party_escape.game;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameResponseDTO> getAll() {
        return gameRepository.findAll().stream().map(GameResponseDTO::new).toList();
    }

}
