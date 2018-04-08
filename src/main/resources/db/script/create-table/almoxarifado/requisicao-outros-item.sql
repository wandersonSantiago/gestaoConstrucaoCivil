CREATE TABLE almoxarifado.requisicao_outros_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	descricao varchar(255) NULL,
	id_area_produto int8 NULL,
	id_requisicao_outros int8 NULL,
	CONSTRAINT requisicao_outros_item_pkey PRIMARY KEY (id),
	CONSTRAINT fk_akwpju4kalx8x7ayj0ju7knqe FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id),
	CONSTRAINT fki0ksnckuv49bvmsw80kybk7k9 FOREIGN KEY (id_area_produto) REFERENCES communs.area_produto(id),
	CONSTRAINT fknt7qmvy9jh37m7f0mc0cdnwf2 FOREIGN KEY (id_requisicao_outros) REFERENCES almoxarifado.requisicao_outros(id)
)