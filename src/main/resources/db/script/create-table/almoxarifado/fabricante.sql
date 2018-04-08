CREATE TABLE almoxarifado.fabricante (
	id int8 NOT NULL,
	id_dado_empresa int8 NOT NULL,
	CONSTRAINT fabricante_pkey PRIMARY KEY (id),
	CONSTRAINT fkiphtgfonvn8ua5hxi9qb2wie8 FOREIGN KEY (id_dado_empresa) REFERENCES communs.dado_empresa(id)
)