package dev.marta_bernardo.party_escape.match;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findById(Long id);
}
