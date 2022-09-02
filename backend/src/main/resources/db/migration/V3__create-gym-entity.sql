CREATE TABLE gym
(
    id        BIGINT       NOT NULL,
    name      VARCHAR(255) NOT NULL,
    school_id BIGINT,
    CONSTRAINT pk_gym PRIMARY KEY (id)
);

ALTER TABLE gym
    ADD CONSTRAINT FK_GYM_ON_SCHOOL FOREIGN KEY (school_id) REFERENCES school (id);