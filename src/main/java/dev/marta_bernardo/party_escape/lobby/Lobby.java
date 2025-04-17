package dev.marta_bernardo.party_escape.lobby;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import java.util.List;

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

    private String joinCode;
    
    /*@OneToMany(mappedBy = "lobby", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches;*/
    
    /*@ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;*/
}
