package dev.marta_bernardo.party_escape.lobby;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LobbyRepository extends JpaRepository<Lobby, Long> {
    Optional<Lobby> findById(Long id);
    List<Lobby> findByAdminId(Long idAdmin);
}
