CREATE TABLE communs.permissao_usuario (
	id int8 NOT NULL,
	permissao_id int8 NULL,
	usuario_id int8 NULL,
	CONSTRAINT permissao_usuario_pkey PRIMARY KEY (id)
)