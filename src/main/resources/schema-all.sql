DROP TABLE category IF EXISTS;
DROP TABLE IF EXISTS game;

CREATE TABLE category  (
    category_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(20),
    type VARCHAR(20),
    characteristics VARCHAR(30)
);

CREATE TABLE game (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    age_recommended INT,
    stock INT
);

INSERT INTO game (title, age_recommended, stock) VALUES
('Apex Legends', 12, 5),
('Fortnite', 16, 0),
('Valorant', 8, 10);