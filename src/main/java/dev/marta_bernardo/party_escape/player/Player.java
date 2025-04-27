package dev.marta_bernardo.party_escape.player;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import dev.marta_bernardo.party_escape.matchdata.MatchProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "finish_datetime")
    private LocalDateTime finishDateTime;

    private Boolean finish;

    @ManyToOne
    @JoinColumn(name = "match_profile_id")
    private MatchProfile matchProfile;
}
