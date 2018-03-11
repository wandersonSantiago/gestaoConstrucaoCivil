CREATE TABLE almoxarifado.requisicao_outros_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	descricao varchar(255) NULL,
	id_area_produto int8 NULL,
	id_requisicao_outros int8 NULL,
	CONSTRAINT requisicao_outros_item_pkey PRIMARY KEY (id)
	 
)