CREATE TABLE communs.aluguel_equipamento (
	id int8 NOT NULL,
	nome_equipamento varchar(255) NULL,
	preco_locacao float8 NULL,
	valor_aquisicao float8 NULL,
	CONSTRAINT aluguel_equipamento_pkey PRIMARY KEY (id)
)