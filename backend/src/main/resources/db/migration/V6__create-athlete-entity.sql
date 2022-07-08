CREATE TABLE athlete
(
    id                  BIGINT NOT NULL,
    first_name          VARCHAR(255),
    last_name           VARCHAR(255),
    social_name         VARCHAR(255),
    birt_date           date,
    address_id          BIGINT,
    weight              DOUBLE PRECISION,
    height              DOUBLE PRECISION,
    biceps_measurement  DOUBLE PRECISION,
    forearm_measurement DOUBLE PRECISION,
    chest_measurement   DOUBLE PRECISION,
    thigh_measurement   DOUBLE PRECISION,
    calf_measurement    DOUBLE PRECISION,
    CONSTRAINT pk_athlete PRIMARY KEY (id)
);

ALTER TABLE athlete
    ADD CONSTRAINT FK_ATHLETE_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);