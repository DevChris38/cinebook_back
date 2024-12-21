DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    username         VARCHAR(250) NOT NULL,
    password         VARCHAR(250) NOT NULL,
    img_profil       VARCHAR(250) NOT NULL,
    firstname        VARCHAR(250) NOT NULL,
    lastname         VARCHAR(250) NOT NULL,
    sexe             VARCHAR(250) NOT NULL,
    phone            VARCHAR(250) NULL,
    email            VARCHAR(250) NOT NULL,
    inscription_date DATE,
    is_premium       BOOLEAN

);

DROP TABLE IF EXISTS role;

CREATE TABLE role
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS customer_role;

CREATE TABLE customer_role
(
    customer_id INT NOT NULL,
    role_id     INT NOT NULL,
    PRIMARY KEY (customer_id, role_id),
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

INSERT INTO role (role_name)
VALUES ('USER');
INSERT INTO role (role_name)
VALUES ('ADMIN');

INSERT INTO customer (username, password, img_profil, firstname, lastname, sexe, phone, email, inscription_date,
                      is_premium)
VALUES ('user', '$2a$10$OfcnM2uq6UxZuzIVoB7KMuLOaoGoJ5NaouNQXwTf6SLetmGXyHIfC',
        'https://firebasestorage.googleapis.com/v0/b/cinebook-c8baa.appspot.com/o/images%2Fea0638e0-2352-4cfe-bce9-c22f0e5a3843?alt=media&token=5bbe7ed0-e169-4dd0-987d-94095fc6201c'
           , 'christopher', 'arthaud', 'MASC', '0609398790', 'christopher.arthaud@gmail.com', '2023-11-26', 1);

INSERT INTO customer (username, password, img_profil, firstname, lastname, sexe, phone, email, inscription_date,
                      is_premium)
VALUES ('admin', '$2a$10$OfcnM2uq6UxZuzIVoB7KMuLOaoGoJ5NaouNQXwTf6SLetmGXyHIfC',
        'https://firebasestorage.googleapis.com/v0/b/cinebook-c8baa.appspot.com/o/images%2Fea0638e0-2352-4cfe-bce9-c22f0e5a3843?alt=media&token=5bbe7ed0-e169-4dd0-987d-94095fc6201c'
           , 'gabrielle', 'gay', 'FEM', '0634360498', 'gabrielle.gay1991@gmail.com', '2024-11-27', 0);

INSERT INTO customer_role (customer_id, role_id)
VALUES (1, 1);
INSERT INTO customer_role (customer_id, role_id)
VALUES (2, 2);