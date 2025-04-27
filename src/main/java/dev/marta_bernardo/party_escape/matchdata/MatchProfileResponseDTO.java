package dev.marta_bernardo.party_escape.matchdata;

import java.time.LocalDateTime;

public record MatchProfileResponseDTO(
    Long id,
    LocalDateTime startDatetime
) {
    public MatchProfileResponseDTO(MatchProfile matchProfile) {
        this(
            matchProfile.getId(),
            matchProfile.getStartDatetime()
        );
    }
}
