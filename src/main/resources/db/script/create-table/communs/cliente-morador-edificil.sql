CREATE TABLE communs.cliente_morador_edificil (
	andar int4 NULL,
	apartamento int4 NULL,
	torre int4 NULL,
	id int8 NOT NULL,
	CONSTRAINT cliente_morador_edificil_pkey PRIMARY KEY (id),
	CONSTRAINT fkj68b5cp5bq4448xrn80oc4p63 FOREIGN KEY (id) REFERENCES communs.cliente_morador(id)
)