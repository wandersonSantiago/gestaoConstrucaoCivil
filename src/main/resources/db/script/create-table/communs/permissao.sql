CREATE TABLE communs.permissao (
	id int8 NOT NULL,
	descricao varchar(255) NULL,
	rol varchar(255) NULL,
	tipo_modulo varchar(255) NULL,
	CONSTRAINT permissao_pkey PRIMARY KEY (id)
)