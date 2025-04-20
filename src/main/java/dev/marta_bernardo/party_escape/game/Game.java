package dev.marta_bernardo.party_escape.game;

//import java.util.HashSet;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import dev.marta_bernardo.party_escape.lobbygame.LobbyGame;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "game")
    private Set<LobbyGame> lobbyGames = new HashSet<>();
}
