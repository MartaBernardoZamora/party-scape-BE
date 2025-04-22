package dev.marta_bernardo.party_escape.matchdata;

import java.time.LocalDateTime;
import java.util.List;

import dev.marta_bernardo.party_escape.player.Player;
import dev.marta_bernardo.party_escape.match.Match;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "match_profiles")
public class MatchProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(name = "start_datetime")
    private LocalDateTime startDatetime;

    @OneToMany(mappedBy = "matchProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

}
