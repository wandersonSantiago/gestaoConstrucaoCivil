CREATE TABLE servicos.servico_edificio (
	andar int4 NULL,
	apartamento int4 NULL,
	torre int4 NULL,
	id int8 NOT NULL,
	CONSTRAINT servico_edificio_pkey PRIMARY KEY (id),
	CONSTRAINT fk38srf829txxm1odv6fbl3jigd FOREIGN KEY (id) REFERENCES servicos.servico_empresa(id)
)