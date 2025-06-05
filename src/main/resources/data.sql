-- Admins
INSERT INTO admins (email, password, username) 
VALUES 
    ('marta@example.com', 'password', 'Marta'),
    ('carlos@example.com', 'password', 'Carlos');

-- Lobby Games
INSERT INTO games (name, description) 
VALUES 
    ('Adivina la palabra', 'Juego de palabras por turnos'),
    ('Escape visual', 'Encuentra objetos ocultos'),
    ('Trivia musical', 'Preguntas sobre canciones');

-- Lobbies
INSERT INTO lobbies (name, admin_id) 
VALUES 
    ('Sala de Marta', 1),
    ('Fiesta de Carlos', 2);

-- Relación Lobby - LobbyGames (asumiendo tabla intermedia lobbies_lobby_games o similar)
INSERT INTO lobbies_games (position, game_id, lobby_id) 
VALUES 
    (1, 1, 1),
    (2, 2, 1),
    (1, 3, 2);