CREATE TABLE almoxarifado.requisicao_edificio_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	andar int4 NULL,
	apartamento int4 NULL,
	torre int4 NULL,
	id_area_produto int8 NULL,
	id_requisicao_edificio int8 NULL,
	CONSTRAINT requisicao_edificio_item_pkey PRIMARY KEY (id)

)