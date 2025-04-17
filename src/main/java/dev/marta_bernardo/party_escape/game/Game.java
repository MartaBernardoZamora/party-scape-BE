package dev.marta_bernardo.party_escape.game;

//import java.util.HashSet;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    //@OneToMany(mappedBy = "game")
    //private Set<LobbyGame> lobbyGames = new HashSet<>();

}
