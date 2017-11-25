CREATE TABLE almoxarifado.tipo_produto (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	descricao varchar(30) NOT NULL,
	CONSTRAINT tipo_produto_pkey PRIMARY KEY (id)
)