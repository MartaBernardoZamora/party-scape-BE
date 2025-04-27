package dev.marta_bernardo.party_escape.matchdata;

import org.springframework.stereotype.Service;

@Service
public class MatchProfileService {

    private final MatchProfileRepository matchProfileRepository;

    public MatchProfileService(MatchProfileRepository matchProfileRepository) {
        this.matchProfileRepository = matchProfileRepository;
    }

    public MatchProfileResponseDTO getById(Long matchId) {
        
        return matchProfileRepository.findById(matchId)
                            .stream()
                            .map(MatchProfileResponseDTO::new)
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Partida no encontrada"));
                
    }

}
