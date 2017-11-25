CREATE TABLE communs.pessoa (
	id int8 NOT NULL,
	cpf varchar(20) NOT NULL,
	data_nascimento date NULL,
	estado_civil varchar(255) NULL,
	idade int4 NOT NULL,
	nome_completo varchar(50) NOT NULL,
	rg varchar(20) NOT NULL,
	sexo varchar(255) NOT NULL,
	telefone_celular varchar(15) NOT NULL,
	telefone_fixo varchar(15) NOT NULL,
	id_endereco int8 NOT NULL,
	CONSTRAINT pessoa_pkey PRIMARY KEY (id),
	CONSTRAINT uk_97gwi9nbsq9hgi7xtclbp7p7d UNIQUE (rg),
	CONSTRAINT uk_nlwiu48rutiltbnjle59krljo UNIQUE (cpf),
	CONSTRAINT fko2v72927b43j7ug0s8b97ymly FOREIGN KEY (id_endereco) REFERENCES communs.endereco(id)
)