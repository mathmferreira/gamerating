CREATE SEQUENCE  IF NOT EXISTS user_id_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE  IF NOT EXISTS game_id_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE  IF NOT EXISTS rating_id_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE user_tb (
   user_id BIGINT NOT NULL,
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
   game_id BIGINT NOT NULL,
   title VARCHAR(255) NOT NULL,
   description VARCHAR(255),
   genre_type VARCHAR(255) NOT NULL CHECK (genre_type in ('ACTION','ACTION_ADVENTURE','ADVENTURE','BOARD_CARD_GAME','PUZZLE','ROLE_PLAYING','SANDBOX','SIMULATION','STRATEGY','SPORTS','MMO')),
   release_date date NOT NULL,
   aud_created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   aud_created_by VARCHAR(255) NOT NULL,
   aud_last_updated_date TIMESTAMP WITHOUT TIME ZONE,
   aud_last_updated_by VARCHAR(255),
   CONSTRAINT pk_game_tb PRIMARY KEY (game_id)
);

CREATE TABLE rating_tb (
   rating_id BIGINT NOT NULL,
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

INSERT INTO user_tb (user_id, name, email, pass, aud_created_date, aud_created_by) VALUES
    (NEXTVAL('user_id_sequence'), 'Admin', 'admin@gmail.com', '$2a$10$WlNtjqYNbS5PiutTe/resuHNeZ7y5cPDRFrm0Xu7lgmbwfhgatnee', CURRENT_TIMESTAMP, 'system');