CREATE TABLE almoxarifado.requisicao_casa_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	andar int4 NOT NULL,
	casa int4 NOT NULL,
	id_area_produto int8 NULL,
	id_requisicao_casa int8 NULL,
	CONSTRAINT requisicao_casa_item_pkey PRIMARY KEY (id)

)