CREATE TABLE Billett
(
    id INT NOT NULL AUTO_INCREMENT,
    film CHAR(255) NOT NULL,
    antall VARCHAR(30) NOT NULL ,
    fornavn VARCHAR(30) NOT NULL,
    etternavn VARCHAR(30) NOT NULL,
    adresse VARCHAR(30) NOT NULL,
    mobilnummer CHAR(20) NOT NULL,
    epost VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);