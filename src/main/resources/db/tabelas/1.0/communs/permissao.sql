 CREATE SEQUENCE communs.permissao_id_seq START 1;

 CREATE TABLE communs.permissao (
	id int8 NOT NULL DEFAULT nextval('communs.permissao_id_seq'::regclass),
	descricao varchar(255) NULL,
	rol varchar(255) NULL,
	tipo_modulo varchar(255) NULL,
	CONSTRAINT permissao_pkey PRIMARY KEY (id)
)