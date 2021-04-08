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
    nome varchar(50) not null,
    descrizione text not null,
    publisher varchar(50) not null,
    imgpath varchar(50) DEFAULT NULL,
    punteggio int not null,
    media_voti int not null default 0,
    PRIMARY KEY (id)
    );
    
CREATE TABLE voto(
	valutazione TINYINT NOT NULL,
	utente VARCHAR(50) NOT NULL,
    Gioco INT NOT NULL,
    FOREIGN KEY (utente)
        REFERENCES utente (username)
        ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (Gioco)
        REFERENCES gioco (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (utente, Gioco)
	);

CREATE TABLE thread (
    idThread INT AUTO_INCREMENT NOT NULL,
    Titolo VARCHAR(50) NOT NULL,
    Testo TEXT NOT NULL,
    utente VARCHAR(50) NOT NULL,
    Idgioco INT NOT NULL,
    FOREIGN KEY (utente)
        REFERENCES utente (username)
        ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (Idgioco)
        REFERENCES gioco (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (idThread)
);

CREATE TABLE richiestagioco (
	id INT NOT NULL AUTO_INCREMENT,
    Utente VARCHAR(50) NOT NULL,
    nomeGioco VARCHAR(50) NOT NULL,
    fonte TEXT NOT NULL,
    risposta BOOLEAN,
    FOREIGN KEY (Utente)
        REFERENCES utente (username)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (id)
);


CREATE TABLE elementolista (
    Utente VARCHAR(50) NOT NULL,
    IdGioco INT NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    FOREIGN KEY (Utente)
        REFERENCES utente (username)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (IdGioco)
        REFERENCES gioco (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (Utente, IdGioco)
);

