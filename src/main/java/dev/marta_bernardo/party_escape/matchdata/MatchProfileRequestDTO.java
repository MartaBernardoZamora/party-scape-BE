package dev.marta_bernardo.party_escape.matchdata;

import java.time.LocalDateTime;
import java.util.List;

public record MatchProfileRequestDTO(
    Long matchId,
    LocalDateTime startDatetime,
    List<Long> playerIds
) {}
