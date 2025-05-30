package dev.marta_bernardo.party_escape.match;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findById(Long id);
    Boolean existsByJoinCode(String joinCode);
    List<Match> findByLobbyId(Long lobbyId);
    Optional<Match> findByJoinCode(String code);
}
