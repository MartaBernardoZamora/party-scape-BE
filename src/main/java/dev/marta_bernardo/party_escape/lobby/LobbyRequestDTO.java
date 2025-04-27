package dev.marta_bernardo.party_escape.lobby;

import java.util.List;

public record LobbyRequestDTO(
    String name,
    List<Long> gameIds
) {}
