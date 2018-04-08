CREATE TABLE almoxarifado.requisicao_casa_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	andar int4 NOT NULL,
	casa int4 NOT NULL,
	id_area_produto int8 NULL,
	id_requisicao_casa int8 NULL,
	CONSTRAINT requisicao_casa_item_pkey PRIMARY KEY (id),
	CONSTRAINT fk_5o70snciavqb8vgh31s0i7n7s FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id),
	CONSTRAINT fket524shhx51yo3nldteg3ku7x FOREIGN KEY (id_requisicao_casa) REFERENCES almoxarifado.requisicao_casa(id),
	CONSTRAINT fkjd6xtrok9rrwicmhl1tlpirko FOREIGN KEY (id_area_produto) REFERENCES communs.area_produto(id)
)