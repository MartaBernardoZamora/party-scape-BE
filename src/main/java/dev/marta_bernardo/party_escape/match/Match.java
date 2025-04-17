package dev.marta_bernardo.party_escape.match;

import java.time.LocalDateTime;

import dev.marta_bernardo.party_escape.lobby.Lobby;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_datetime")
    private LocalDateTime startDatetime;

    @ManyToOne
    @JoinColumn(name = "lobby_id")
    private Lobby lobby;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    //@OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Player> players;

}
