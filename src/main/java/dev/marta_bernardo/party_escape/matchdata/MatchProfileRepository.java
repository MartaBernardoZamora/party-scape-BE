package dev.marta_bernardo.party_escape.matchdata;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchProfileRepository extends JpaRepository<MatchProfile, Long> {
    Optional<MatchProfile> findByMatchId(Long matchId);

}
