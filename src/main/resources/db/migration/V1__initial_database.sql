CREATE SEQUENCE  IF NOT EXISTS user_id_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE  IF NOT EXISTS game_id_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE  IF NOT EXISTS rating_id_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE user_tb (
   user_id BIGINT NOT NULL DEFAULT NEXTVAL('user_id_sequence'),
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   pass VARCHAR(255) NOT NULL,
   aud_created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   aud_created_by VARCHAR(255) NOT NULL,
   aud_last_updated_date TIMESTAMP WITHOUT TIME ZONE,
   aud_last_updated_by VARCHAR(255),
   CONSTRAINT pk_user_tb PRIMARY KEY (user_id)
);

CREATE TABLE game_tb (
   game_id BIGINT NOT NULL DEFAULT NEXTVAL('game_id_sequence'),
   title VARCHAR(255) NOT NULL,
   description VARCHAR(255),
   genre_type VARCHAR(255) NOT NULL CHECK (genre_type in ('ACTION','ACTION_ADVENTURE','ADVENTURE','BOARD_CARD_GAME','MMO','MOBA','PUZZLE','ROLE_PLAYING','SANDBOX','SIMULATION','STRATEGY','SPORTS')),
   release_date date NOT NULL,
   aud_created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   aud_created_by VARCHAR(255) NOT NULL,
   aud_last_updated_date TIMESTAMP WITHOUT TIME ZONE,
   aud_last_updated_by VARCHAR(255),
   CONSTRAINT pk_game_tb PRIMARY KEY (game_id)
);

CREATE TABLE rating_tb (
   rating_id BIGINT NOT NULL DEFAULT NEXTVAL('rating_id_sequence'),
   value INTEGER NOT NULL,
   comments VARCHAR(255),
   user_id BIGINT NOT NULL,
   game_id BIGINT NOT NULL,
   aud_created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   aud_created_by VARCHAR(255) NOT NULL,
   aud_last_updated_date TIMESTAMP WITHOUT TIME ZONE,
   aud_last_updated_by VARCHAR(255),
   CONSTRAINT pk_rating_tb PRIMARY KEY (rating_id)
);

ALTER TABLE user_tb ADD CONSTRAINT uc_user_tb_email UNIQUE (email);

ALTER TABLE rating_tb ADD CONSTRAINT fk_game_rating FOREIGN KEY (game_id) REFERENCES game_tb (game_id);

ALTER TABLE rating_tb ADD CONSTRAINT fk_user_rating FOREIGN KEY (user_id) REFERENCES user_tb (user_id);

INSERT INTO user_tb (name, email, pass, aud_created_date, aud_created_by) VALUES
    ('Admin', 'admin@gmail.com', '$2a$10$WlNtjqYNbS5PiutTe/resuHNeZ7y5cPDRFrm0Xu7lgmbwfhgatnee', CURRENT_TIMESTAMP, 'system');

INSERT INTO game_tb (title, description, genre_type, release_date, aud_created_date, aud_created_by) VALUES
    ('God Of War', 'The game is the eighth installment in the God of War series. This installment is loosely inspired by Norse mythology, with the majority of it set in ancient Scandinavia in the realm of Midgard.', 'ACTION_ADVENTURE', '2018-04-20', '2018-04-20', 'system'),
    ('Yu-Gi-Oh! Master Duel', 'The game a free-to-play digital collectible card game based on the Yu-Gi-Oh! Trading Card Game, developed and published by Konami.', 'BOARD_CARD_GAME', '2022-01-19', '2022-01-19', 'system'),
    ('League of Legends', 'Commonly referred to as League, is a multiplayer online battle arena video game developed and published by Riot Games, the game was inspired by Defense of the Ancients, a custom map for Warcraft III.', 'MOBA', '2009-10-27', '2009-10-27', 'system'),
    ('Devil May Cry 5', 'It is a hack and slash eletronic game developed and published by Capcom. It is the sixth installment overall and the fifth mainline installment in the Devil May Cry series.', 'ACTION_ADVENTURE', '2019-03-08', '2019-03-08', 'system'),
    ('FIFA 23', 'Is a football video game published by EA Sports. It is the 30th installment in the FIFA series that is developed by EA Sports, the final installment under the FIFA banner.', 'SPORTS', '2022-09-30', '2022-09-30', 'system'),
    ('Counter-Strike: Global Offensive', 'Is a multiplayer tactical first-person shooter developed by Valve and Hidden Path Entertainment. The game pits two teams, Terrorists and Counter-Terrorists, against each other in different objective-based game modes.', 'ACTION', '2012-08-21', '2012-08-21', 'system'),
    ('Ori and the Blind Forest', 'Is a video game developed by Moon Studios and published by Microsoft Studios. Players assume control of Ori, a small white spirit, and Sein, the "light and eyes" of the Spirit Tree.', 'ADVENTURE', '2015-03-11', '2015-03-11', 'system'),
    ('Tricky Towers', 'Is a physics based tower building game puzzle video game that uses a form of the block-stacking problem as the central game mechanic.', 'PUZZLE', '2016-08-02', '2016-08-02', 'system'),
    ('Perfect World', 'is a 3D adventure and fantasy MMORPG with traditional Chinese settings. Players can take on various roles depending on choice of race and choice of class within that race.', 'MMO', '2005-07-01', '2005-07-01', 'system'),
    ('The Sims 4', 'Allows players to create and dress characters called "Sims", build and furnish houses for them, and simulate their everyday lives.', 'SIMULATION', '2014-09-02', '2014-09-02', 'system');
