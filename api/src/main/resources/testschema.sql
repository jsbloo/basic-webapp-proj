DROP TABLE IF EXISTS factoid CASCADE;

CREATE TABLE factoid(
    id INT AUTO_INCREMENT,
    axiom smallint(1) NOT NULL,
    content VARCHAR(255) NOT NULL,
    explanation VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user CASCADE;

CREATE TABLE user(
    id INT AUTO_INCREMENT,
    active smallint(1),
    username VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    roles VARCHAR(255),
    PRIMARY KEY (id)
);
