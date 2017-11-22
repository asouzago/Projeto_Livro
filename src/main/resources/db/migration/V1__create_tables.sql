CREATE TABLE editora(
   id          INT              NOT NULL IDENTITY,
   nome        VARCHAR (250)    NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE autor(
   id          INT              NOT NULL IDENTITY,
   nome        VARCHAR (250)    NOT NULL,
   sexo        CHAR(1)                  ,
   PRIMARY KEY (id)
);

CREATE TABLE livro(
   id          INT              NOT NULL IDENTITY,
   nome        VARCHAR (250)    NOT NULL,
   ano         INT              ,
   autor_id    INT              NOT NULL,
   editora_id  INT				,
   volume	   INT				,
   FOREIGN KEY (autor_id) REFERENCES autor(id),
   FOREIGN KEY (editora_id) REFERENCES editora(id),
   PRIMARY KEY (id)
);

CREATE TABLE livro_autor(
   livro_id          INT              NOT NULL,
   autor_id           INT              NOT NULL,
   FOREIGN KEY (livro_id) REFERENCES livro(id),
   FOREIGN KEY (autor_id)  REFERENCES autor(id),
   PRIMARY KEY (livro_id, autor_id)
);

CREATE TABLE voto(
   id              INT                NOT NULL IDENTITY,
   livro_id        INT                NOT NULL,
   estrela         INT                NOT NULL,
   FOREIGN KEY (livro_id) REFERENCES livro(id),
   PRIMARY KEY (id)
);
