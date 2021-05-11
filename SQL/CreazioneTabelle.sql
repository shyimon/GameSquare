DROP DATABASE IF EXISTS gamesquare;
CREATE DATABASE gamesquare;
use gamesquare;


CREATE TABLE utente (
  username VARCHAR(50) NOT NULL,
  email VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  punteggio INT NOT NULL DEFAULT 0,
  tipo varchar(15) NOT NULL DEFAULT 'user',
  PRIMARY KEY (username)
  );
  
CREATE TABLE gioco (
	id INT NOT NULL AUTO_INCREMENT,
    nome varchar(50) NOT NULL,
    descrizione text NOT NULL,
    publisher varchar(50) NOT NULL,
    anno varchar(4) NOT NULL,
    genere varchar(30) NOT NULL,
    imgpath varchar(50) DEFAULT 'img/Games/placeholder',
    punteggio int NOT NULL,
    media_voti FLOAT DEFAULT 0,
    PRIMARY KEY (id)
    );
    
CREATE TABLE voto(
	valutazione TINYINT NOT NULL,
	utente VARCHAR(50) NOT NULL,
    id_gioco INT NOT NULL,
    FOREIGN KEY (utente)
        REFERENCES utente (username)
        ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (id_gioco)
        REFERENCES gioco (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (utente, id_gioco)
	);

CREATE TABLE thread (
    id_thread INT AUTO_INCREMENT NOT NULL,
	tipo_thread VARCHAR(50) NOT NULL,
    titolo VARCHAR(50) NOT NULL,
    testo TEXT NOT NULL,
    utente VARCHAR(50) NOT NULL,
    id_gioco INT NOT NULL,
    FOREIGN KEY (utente)
        REFERENCES utente (username)
        ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (id_gioco)
        REFERENCES gioco (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (id_thread)
);

CREATE TABLE richiesta_gioco (
	id INT NOT NULL AUTO_INCREMENT,
    utente VARCHAR(50) NOT NULL,
    nome_gioco VARCHAR(50) NOT NULL,
    publisher varchar(50) NOT NULL,
    anno varchar(4) NOT NULL,
    genere varchar(30) NOT NULL,
    fonte TEXT NOT NULL,
    FOREIGN KEY (utente)
        REFERENCES utente (username)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (id)
);


CREATE TABLE elemento_lista (
    utente VARCHAR(50) NOT NULL,
    id_gioco INT NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    FOREIGN KEY (utente)
        REFERENCES utente (username)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_gioco)
        REFERENCES gioco (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (utente, id_gioco)
);

