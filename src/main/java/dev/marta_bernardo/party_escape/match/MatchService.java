package dev.marta_bernardo.party_escape.match;

import java.util.List;
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
        Lobby lobby = getLobbyOrThrow(request.lobbyId());
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
    public List<MatchResponseDTO> getByLobbyId(Long lobbyId) {
        getLobbyOrThrow(lobbyId);
        List<Match> matches = matchRepository.findByLobbyId(lobbyId);
        if(matches.isEmpty()){
            throw new EntityNotFoundException("No hay partidas en esta sala");
        }
        return matchRepository.findByLobbyId(lobbyId)
                            .stream()
                            .map(MatchResponseDTO::new)
                            .toList();
    }
    private Lobby getLobbyOrThrow(Long lobbyId) {
        return lobbyRepository.findById(lobbyId)
            .orElseThrow(() -> new EntityNotFoundException("Lobby no encontrado"));
    }
    public MatchResponseDTO getById(Long matchId) {
        Match match = matchRepository.findById(matchId)
            .orElseThrow(() -> new EntityNotFoundException("Partida no encontrada"));
        return new MatchResponseDTO(match);
    }
    public void delete(Long matchId) {
        Match match = matchRepository.findById(matchId)
            .orElseThrow(() -> new EntityNotFoundException("Partida no encontrada"));
        matchRepository.delete(match);
    }

}
