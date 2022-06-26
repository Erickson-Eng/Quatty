CREATE TABLE school
(
    id         BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    address_id BIGINT,
    CONSTRAINT pk_school PRIMARY KEY (id)
);

ALTER TABLE school
    ADD CONSTRAINT FK_SCHOOL_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);