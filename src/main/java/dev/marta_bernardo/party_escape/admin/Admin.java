package dev.marta_bernardo.party_escape.admin;

import java.util.Set;
import java.util.HashSet;

import dev.marta_bernardo.party_escape.lobby.Lobby;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String username;

    @OneToMany(mappedBy = "admin")
    private Set<Lobby> lobbies= new HashSet<>();
}
