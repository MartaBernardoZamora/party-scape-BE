package dev.marta_bernardo.party_escape.lobby;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev.marta_bernardo.party_escape.lobbygame.LobbyGame;
import dev.marta_bernardo.party_escape.match.Match;
import dev.marta_bernardo.party_escape.admin.Admin;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lobbies")
public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @OneToMany(mappedBy = "lobby", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches;

    @OneToMany(mappedBy = "lobby")
    private Set<LobbyGame> lobbyGames = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
