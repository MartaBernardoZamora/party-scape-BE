package dev.marta_bernardo.party_escape.match;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.marta_bernardo.party_escape.lobby.Lobby;
import jakarta.persistence.EntityNotFoundException;

import dev.marta_bernardo.party_escape.lobby.LobbyRepository;
import dev.marta_bernardo.party_escape.matchdata.MatchProfile;
import dev.marta_bernardo.party_escape.matchdata.MatchProfileRepository;
import dev.marta_bernardo.party_escape.player.Player;
import dev.marta_bernardo.party_escape.player.PlayerRepository;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final LobbyRepository lobbyRepository;
    private final MatchProfileRepository matchProfileRepository;
    private final PlayerRepository playerRepository;

    public MatchService(MatchRepository matchRepository, LobbyRepository lobbyRepository, MatchProfileRepository matchProfileRepository, PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.lobbyRepository = lobbyRepository;
        this.matchProfileRepository = matchProfileRepository;
        this.playerRepository = playerRepository;
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
    public MatchResponseDTO update(Long matchId, MatchRequestDTO request) {
        Match match = matchRepository.findById(matchId)
            .orElseThrow(() -> new EntityNotFoundException("Partida no encontrada"));
        match.setStatus(request.status());

        if ("IN_PROGRESS".equals(request.status().name())) {
            MatchProfile matchProfile = new MatchProfile();
            matchProfile.setMatch(match);
            matchProfile.setStartDatetime(LocalDateTime.now());
            matchProfileRepository.save(matchProfile);
        }
        if("FINISHED".equals(request.status().name())) {
            MatchProfile matchProfile = matchProfileRepository.findByMatchId(matchId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Partida no encontrada"));
            request.results().forEach(player -> {
                Player playerEntity = new Player();
                playerEntity.setMatchProfile(matchProfile);
                playerEntity.setName(player.playerName());
                playerEntity.setFinish(player.finished());
                playerEntity.setFinishDateTime(player.finalTime());
                playerRepository.save(playerEntity);
            });
            
        }
        matchRepository.save(match);
        return new MatchResponseDTO(match);
    }
    public void delete(Long matchId) {
        Match match = matchRepository.findById(matchId)
            .orElseThrow(() -> new EntityNotFoundException("Partida no encontrada"));
        matchRepository.delete(match);
    }
    public MatchResponseDTO getByJoinCode(String code) {
        Match match = matchRepository.findByJoinCode(code)
            .orElseThrow(() -> new EntityNotFoundException("Partida no encontrada"));
        return new MatchResponseDTO(match);
    }

}
