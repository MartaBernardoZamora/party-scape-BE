-- Admins
INSERT INTO admins (id, email, password, username) 
VALUES 
    (1, 'martaexample.com', 'password', 'Marta'),
    (2, 'carlosexample.com', 'password', 'Carlos');

-- Lobby Games
INSERT INTO games (id, name, description) 
VALUES 
    (1, 'Adivina la palabra', 'Juego de palabras por turnos'),
    (2, 'Escape visual', 'Encuentra objetos ocultos'),
    (3, 'Trivia musical', 'Preguntas sobre canciones');

-- Lobbies
INSERT INTO lobbies (id, name, admin_id) 
VALUES 
    (1, 'Sala de Marta', 1),
    (2, 'Fiesta de Carlos', 2);

-- Relación Lobby - LobbyGames (asumiendo tabla intermedia lobbies_lobby_games o similar)
INSERT INTO lobbies_games (lobby_id, game_id) 
VALUES 
    (1, 1),
    (1, 2),
    (2, 3);