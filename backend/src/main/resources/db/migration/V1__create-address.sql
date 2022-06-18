CREATE TABLE address
(
    id         BIGINT       NOT NULL,
    street     VARCHAR(255) NOT NULL,
    complement VARCHAR(255),
    zip_code   VARCHAR(8)   NOT NULL,
    uf         VARCHAR(2)   NOT NULL,
    city       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);