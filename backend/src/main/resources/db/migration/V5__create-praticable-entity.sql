CREATE TABLE gym_sport_practicable
(
    id          BIGINT       NOT NULL,
    status      VARCHAR(255) NOT NULL,
    description TEXT,
    sport_id    BIGINT       NOT NULL,
    gym_id      BIGINT       NOT NULL,
    CONSTRAINT pk_gym_sport_practicable PRIMARY KEY (id)
);

ALTER TABLE gym_sport_practicable
    ADD CONSTRAINT FK_GYM_SPORT_PRACTICABLE_ON_GYM FOREIGN KEY (gym_id) REFERENCES gym (id);

ALTER TABLE gym_sport_practicable
    ADD CONSTRAINT FK_GYM_SPORT_PRACTICABLE_ON_SPORT FOREIGN KEY (sport_id) REFERENCES sport (id);