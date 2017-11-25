CREATE TABLE almoxarifado.transferencia_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	id_transferencia int8 NULL,
	CONSTRAINT transferencia_item_pkey PRIMARY KEY (id),
	CONSTRAINT fk4rrrdja6x52qugntd2i1911i9 FOREIGN KEY (id_transferencia) REFERENCES almoxarifado.transferencia(id),
	CONSTRAINT fk_o8og11ycjyq85w7wya8t8uuro FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id)
)