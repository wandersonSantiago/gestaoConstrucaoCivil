CREATE TABLE communs.categoria (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	descricao varchar(25) NOT NULL,
	tipo_categoria varchar(255) NOT NULL,
	CONSTRAINT categoria_pkey PRIMARY KEY (id)
)