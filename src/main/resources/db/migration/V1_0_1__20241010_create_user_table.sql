DROP TABLE IF EXISTS project;

CREATE TABLE project
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    img         INT          NOT NULL,
    description VARCHAR(250) NOT NULL,
    link        VARCHAR(250) NULL,
    type        VARCHAR(250) NOT NULL,
    year        INT          NOT NULL,
    FOREIGN KEY (img) REFERENCES image (id)
);

DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    username         VARCHAR(250) NOT NULL,
    password         VARCHAR(250) NOT NULL,
    img_profil       INT          NULL,
    firstname        VARCHAR(250) NOT NULL,
    lastname         VARCHAR(250) NOT NULL,
    sexe             VARCHAR(250) NOT NULL,
    phone            VARCHAR(250) NULL,
    email            VARCHAR(250) NOT NULL,
    project_id       INT          NULL,
    inscription_date DATE,
    is_premium       BOOLEAN,
    FOREIGN KEY (img_profil) REFERENCES image (id),
    FOREIGN KEY (project_id) REFERENCES project (id)
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