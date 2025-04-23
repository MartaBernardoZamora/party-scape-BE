package dev.marta_bernardo.party_escape.match;

public record MatchResponseDTO(
    Long id,
    String joinCode,
    MatchStatus status
){
    public MatchResponseDTO(Match match) {
        this(match.getId(),
            match.getJoinCode(),
            match.getStatus()
        );
    }
}
