package dev.marta_bernardo.party_escape.match;

import java.time.LocalDateTime;

public record MatchResponseDTO(
    Long id,
    String joinCode,
    MatchStatus status,
    LocalDateTime startDatetime
){
    public MatchResponseDTO(Match match) {
        this(match.getId(),
            match.getJoinCode(),
            match.getStatus(),
            match.getMatchProfile() != null ? match.getMatchProfile().getStartDatetime() : null
        );
    }
}
