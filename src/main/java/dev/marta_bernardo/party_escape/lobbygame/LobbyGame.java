package dev.marta_bernardo.party_escape.lobbygame;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dev.marta_bernardo.party_escape.game.Game;
import dev.marta_bernardo.party_escape.lobby.Lobby;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lobbies_games")
public class LobbyGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lobby_id")
    private Lobby lobby;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private Integer position;
}
