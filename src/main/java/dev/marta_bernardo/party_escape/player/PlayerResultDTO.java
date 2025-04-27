package dev.marta_bernardo.party_escape.player;

import java.time.LocalDateTime;

public record PlayerResultDTO(
    String playerName,
    LocalDateTime finalTime,
    Boolean finished
){}
