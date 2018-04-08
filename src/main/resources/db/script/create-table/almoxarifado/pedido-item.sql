CREATE TABLE almoxarifado.pedido_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	id_pedido_compra int8 NULL,
	CONSTRAINT pedido_item_pkey PRIMARY KEY (id),
	CONSTRAINT fk_5guwi7yl0kr0jhrqqp8ciiy8l FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id),
	CONSTRAINT fkpxbymligfrhcod4fwex0bf9pq FOREIGN KEY (id_pedido_compra) REFERENCES almoxarifado.pedido_compra(id)
)