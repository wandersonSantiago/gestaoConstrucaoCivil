CREATE TABLE almoxarifado.transferencia_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	id_transferencia int8 NULL,
	CONSTRAINT transferencia_item_pkey PRIMARY KEY (id)
)