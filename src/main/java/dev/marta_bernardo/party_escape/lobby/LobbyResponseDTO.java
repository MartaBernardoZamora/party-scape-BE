package dev.marta_bernardo.party_escape.lobby;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import dev.marta_bernardo.party_escape.match.Match;

public record LobbyResponseDTO(
    Long id,
    String name,
    List<Long> matchIds,
    Set<Long> lobbyGameIds,
    Long adminId
) {
    public LobbyResponseDTO(Lobby lobby) {
        this(
            lobby.getId(), 
            lobby.getName(), 
            lobby.getMatches().stream().map(Match::getId).toList(), 
            lobby.getLobbyGames().stream()
                .map(lg -> lg.getGame().getId())
                .collect(Collectors.toSet()),           
            lobby.getAdmin().getId()
        );
    }
}
