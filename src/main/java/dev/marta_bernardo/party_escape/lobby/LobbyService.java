package dev.marta_bernardo.party_escape.lobby;

import org.springframework.stereotype.Service;

import dev.marta_bernardo.party_escape.lobbygame.LobbyGame;
import dev.marta_bernardo.party_escape.game.Game;
import dev.marta_bernardo.party_escape.game.GameRepository;
import dev.marta_bernardo.party_escape.lobbygame.LobbyGameRepository;
import dev.marta_bernardo.party_escape.admin.AdminRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LobbyService {
    LobbyRepository lobbyRepository;
    AdminRepository adminRepository;
    LobbyGameRepository lobbyGameRepository;
    GameRepository gameRepository;

    public LobbyService(LobbyRepository lobbyRepository, AdminRepository adminRepository, LobbyGameRepository lobbyGameRepository, GameRepository gameRepository) {
        this.lobbyRepository = lobbyRepository;
        this.adminRepository = adminRepository;
        this.lobbyGameRepository = lobbyGameRepository;
        this.gameRepository = gameRepository;
    }

    public List<LobbyResponseDTO> getAll(Long adminId) {
        adminRepository.findById(adminId)
            .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + adminId));
        return lobbyRepository.findByAdminId(adminId)
                              .stream()
                              .map(LobbyResponseDTO::new)
                              .toList();
    }

    public LobbyResponseDTO getById(Long id) {
        return lobbyRepository.findById(id)
                              .map(LobbyResponseDTO::new)
                              .orElseThrow(() -> new RuntimeException("Lobby not found"));
    }

    public LobbyResponseDTO create(LobbyRequestDTO request, Long adminId) {
        adminRepository.findById(adminId)
            .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + adminId));
        
        Lobby lobby = new Lobby();
        lobby.setName(request.name());
        lobby.setAdmin(adminRepository.findById(adminId).get());

        lobby.setLobbyGames(buildLobbyGames(request.gameIds(), lobby));

        return new LobbyResponseDTO(lobbyRepository.save(lobby));
    }
    public LobbyResponseDTO update(Long id, LobbyRequestDTO request){
        return lobbyRepository.findById(id)
                            .map(lobby -> {
                                lobby.setName(request.name());
                                lobby.getLobbyGames().clear();
                                lobby.getLobbyGames().addAll(buildLobbyGames(request.gameIds(), lobby));
                                return new LobbyResponseDTO(lobbyRepository.save(lobby));
                            })
                            .orElseThrow(() -> new RuntimeException("Lobby not found"));
    }
    public void delete(Long id) {
        Lobby lobby = lobbyRepository.findById(id)
                                    .orElseThrow(() -> new RuntimeException("Lobby not found with ID: " + id));
        lobbyRepository.delete(lobby);
    }
    private Set<LobbyGame> buildLobbyGames(List<Long> gameIds, Lobby lobby) {
        AtomicInteger positionCounter = new AtomicInteger(1);
    
        return gameIds.stream()
            .map(gameId -> {
                Game game = gameRepository.findById(gameId)
                    .orElseThrow(() -> new RuntimeException("Game not found with ID: " + gameId));
                LobbyGame lobbyGame = new LobbyGame();
                lobbyGame.setLobby(lobby);
                lobbyGame.setGame(game);
                lobbyGame.setPosition(positionCounter.getAndIncrement());
                return lobbyGame;
            })
            .collect(Collectors.toSet());
    }

}
