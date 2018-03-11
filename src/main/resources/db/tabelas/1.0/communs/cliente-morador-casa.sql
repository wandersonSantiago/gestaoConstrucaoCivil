CREATE TABLE communs.cliente_morador_casa (
	numero_casa int4 NOT NULL,
	id int8 NOT NULL,
	CONSTRAINT cliente_morador_casa_numero_casa_check CHECK ((numero_casa <= 10)),
	CONSTRAINT cliente_morador_casa_pkey PRIMARY KEY (id)
	 
)
