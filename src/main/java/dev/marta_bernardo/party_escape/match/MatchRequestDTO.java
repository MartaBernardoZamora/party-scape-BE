package dev.marta_bernardo.party_escape.match;

import java.util.List;

import dev.marta_bernardo.party_escape.player.PlayerResultDTO;

public record MatchRequestDTO(
    Long lobbyId,
    MatchStatus status,
    List<PlayerResultDTO> results
) {}