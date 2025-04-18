package dev.marta_bernardo.party_escape.lobby;

import java.util.Set;

public record LobbyRequestDTO(
    String name, 
    Long adminId,
    Set<Long> lobbyGameIds
) {}
