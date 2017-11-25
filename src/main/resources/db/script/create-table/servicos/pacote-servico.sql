CREATE TABLE servicos.pacote_servico (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	codigo int4 NOT NULL,
	criterio_servico varchar(250) NOT NULL,
	descricao varchar(50) NOT NULL,
	servicos_atribuidos varchar(250) NOT NULL,
	valor float8 NOT NULL,
	id_categoria int8 NULL,
	CONSTRAINT pacote_servico_pkey PRIMARY KEY (id),
	CONSTRAINT fk1il4dcmgqg7ynmr01uindlwgx FOREIGN KEY (id_categoria) REFERENCES communs.categoria(id)
)