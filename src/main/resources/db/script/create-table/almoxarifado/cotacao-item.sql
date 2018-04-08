CREATE TABLE almoxarifado.cotacao_item (
	id int8 NOT NULL,
	descricao varchar(255) NOT NULL,
	quantidade int4 NOT NULL,
	id_cotacao int8 NULL,
	CONSTRAINT cotacao_item_pkey PRIMARY KEY (id),
	CONSTRAINT fk6dvm4jhys9bnsu2ht26blw11x FOREIGN KEY (id_cotacao) REFERENCES almoxarifado.cotacao(id)
)