DROP TABLE IF EXISTS image;

CREATE TABLE image
(
    id   int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    path varchar(500),
    link varchar(500)
);