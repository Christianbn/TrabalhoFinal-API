CREATE TABLE usuario (
   id serial PRIMARY key,
   nome varchar(60),
   email varchar(60),
   senha varchar(255)
);

CREATE TABLE perfil (
   id_perfil serial PRIMARY KEY,
   nome varchar(40)
);

CREATE TABLE usuario_perfil (
	id_usuario int REFERENCES usuario(id),
	id_perfil int REFERENCES perfil(id_perfil),
	data_criacao date,
	CONSTRAINT pk_usuario_perfil PRIMARY KEY (id_usuario, id_perfil)
);

INSERT INTO perfil (nome) VALUES
('ADMIN'),
('USER');