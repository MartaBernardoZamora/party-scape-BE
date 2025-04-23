package dev.marta_bernardo.party_escape.match;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.marta_bernardo.party_escape.lobby.Lobby;
import jakarta.persistence.EntityNotFoundException;

import dev.marta_bernardo.party_escape.lobby.LobbyRepository;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final LobbyRepository lobbyRepository;

    public MatchService(MatchRepository matchRepository, LobbyRepository lobbyRepository) {
        this.matchRepository = matchRepository;
        this.lobbyRepository = lobbyRepository;
    }
    public MatchResponseDTO create(MatchRequestDTO request){
        Lobby lobby = lobbyRepository.findById(request.lobbyId())
            .orElseThrow(() -> new EntityNotFoundException("Lobby no encontrado"));
        String status= "CREATED";
        Match match = new Match(generateUniqueJoinCode(), lobby, status);
        matchRepository.save(match);
        return new MatchResponseDTO(match);
    }
    public String generateUniqueJoinCode() {
        String code;
        do {
            code = UUID.randomUUID().toString().substring(0, 8).toUpperCase(); 
        } while (matchRepository.existsByJoinCode(code));
        return code;
    }

}
