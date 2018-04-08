CREATE TABLE almoxarifado.pedido_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	id_pedido_compra int8 NULL,
	CONSTRAINT pedido_item_pkey PRIMARY KEY (id)
)