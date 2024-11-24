DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL
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

INSERT INTO customer (username, password)
VALUES ('user', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.');
INSERT INTO customer (username, password)
VALUES ('admin', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC');

INSERT INTO customer_role (customer_id, role_id)
VALUES (1, 1);
INSERT INTO customer_role (customer_id, role_id)
VALUES (2, 2);