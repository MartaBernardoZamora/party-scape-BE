package dev.marta_bernardo.party_escape.game;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName(String name);
}
