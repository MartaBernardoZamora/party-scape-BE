package dev.marta_bernardo.party_escape.game;

public record GameResponseDTO(
    Long id,
    String name,
    String description
) {
    public GameResponseDTO(Game game) {
        this(
            game.getId(), 
            game.getName(), 
            game.getDescription()
        );
    }
}
