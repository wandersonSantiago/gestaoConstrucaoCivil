CREATE TABLE servicos.servico_casa (
	andar int4 NULL,
	numero int4 NULL,
	id int8 NOT NULL,
	CONSTRAINT servico_casa_pkey PRIMARY KEY (id),
	CONSTRAINT fkn0si5f3t2ky38o6oa8ikldq36 FOREIGN KEY (id) REFERENCES servicos.servico_empresa(id)
)