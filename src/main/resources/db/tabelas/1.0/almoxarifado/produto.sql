CREATE TABLE almoxarifado.produto (
	id int8 NOT NULL,
	ativo bool NULL,
	codigo int4 NULL,
	codigo_barra varchar(50) NOT NULL,
	descricao varchar(50) NOT NULL,
	unidade_medida varchar(255) NULL,
	id_categoria int8 NULL,
	id_fabricante int8 NULL,
	id_tipo int8 NULL,
	id_usuario_cadastro int8 NULL,
	CONSTRAINT produto_pkey PRIMARY KEY (id)
)