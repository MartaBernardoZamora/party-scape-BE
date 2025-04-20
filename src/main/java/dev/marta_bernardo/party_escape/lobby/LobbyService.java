package dev.marta_bernardo.party_escape.lobby;

import org.springframework.stereotype.Service;

import dev.marta_bernardo.party_escape.lobbygame.LobbyGame;
import dev.marta_bernardo.party_escape.lobbygame.LobbyGameRepository;
import dev.marta_bernardo.party_escape.admin.AdminRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;

@Service
public class LobbyService {
    LobbyRepository lobbyRepository;
    AdminRepository adminRepository;
    LobbyGameRepository lobbyGameRepository;

    public LobbyService(LobbyRepository lobbyRepository, AdminRepository adminRepository, LobbyGameRepository lobbyGameRepository) {
        this.lobbyRepository = lobbyRepository;
        this.adminRepository = adminRepository;
        this.lobbyGameRepository = lobbyGameRepository;
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

        Set<LobbyGame> lobbyGames = request.lobbyGameIds()
                                            .stream()
                                            .map(id -> lobbyGameRepository.findById(id)
                                            .orElseThrow(() -> new RuntimeException("LobbyGame not found with ID: " + id)))
                                            .collect(Collectors.toSet());

        Lobby lobby = new Lobby();
        lobby.setName(request.name());
        lobby.setLobbyGames(lobbyGames);

        return new LobbyResponseDTO(lobbyRepository.save(lobby));
    }
    public LobbyResponseDTO update(Long id, LobbyRequestDTO request){
        return lobbyRepository.findById(id)
                            .map(lobby -> {
                                lobby.setName(request.name());
                                lobby.setLobbyGames(request.lobbyGameIds().stream()
                                    .map(gameId -> lobbyGameRepository.findById(gameId)
                                    .orElseThrow(() -> new RuntimeException("LobbyGame not found with ID: " + gameId)))
                                    .collect(Collectors.toSet()));                                
                                return new LobbyResponseDTO(lobbyRepository.save(lobby));
                            })
                            .orElseThrow(() -> new RuntimeException("Lobby not found"));
    }
    public void delete(Long id) {
        Lobby lobby = lobbyRepository.findById(id)
                                    .orElseThrow(() -> new RuntimeException("Lobby not found with ID: " + id));
        lobbyRepository.delete(lobby);
    }

}
