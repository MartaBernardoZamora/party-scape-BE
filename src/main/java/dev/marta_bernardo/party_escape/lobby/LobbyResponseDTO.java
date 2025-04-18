package dev.marta_bernardo.party_escape.lobby;

import java.util.List;
import java.util.Set;

public record LobbyResponseDTO(
    Long id,
    String name,
    Long adminId,
    List<Long> matchIds,
    Set<Long> lobbyGameIds
) {}
