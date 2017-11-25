CREATE TABLE communs.empresa_contratante (
	id int8 NOT NULL,
	id_dado_empresa int8 NOT NULL,
	CONSTRAINT empresa_contratante_pkey PRIMARY KEY (id),
	CONSTRAINT fkqqhnmknub93nv5jbspbmu8e2e FOREIGN KEY (id_dado_empresa) REFERENCES communs.dado_empresa(id)
)