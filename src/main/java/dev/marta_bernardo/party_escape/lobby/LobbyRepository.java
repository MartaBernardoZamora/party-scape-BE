package dev.marta_bernardo.party_escape.lobby;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LobbyRepository extends JpaRepository<Lobby, Long> {
    Optional<Lobby> findByUserId(Long id);    
}
