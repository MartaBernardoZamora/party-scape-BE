package dev.marta_bernardo.party_escape.match;

import dev.marta_bernardo.party_escape.lobby.Lobby;
import dev.marta_bernardo.party_escape.matchdata.MatchProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    public Match(String joinCode, Lobby lobby, String status) {
        this.joinCode = joinCode;
        this.lobby = lobby;
        this.status = MatchStatus.valueOf(status);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "join_code")
    private String joinCode;

    @ManyToOne
    @JoinColumn(name = "lobby_id")
    private Lobby lobby;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    @OneToOne(mappedBy = "match", cascade = CascadeType.ALL)
    private MatchProfile matchProfile;
}
