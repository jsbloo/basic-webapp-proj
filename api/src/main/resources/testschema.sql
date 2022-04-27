DROP TABLE IF EXISTS factoid CASCADE;

CREATE TABLE factoid(
    id INT AUTO_INCREMENT,
    axiom smallint(1) NOT NULL,
    content VARCHAR(255) NOT NULL,
    explanation VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);