CREATE TABLE almoxarifado.cotacao_empresa_item (
	id int8 NOT NULL,
	observacao varchar(255) NULL,
	status varchar(255) NULL,
	valor_unitario float8 NOT NULL,
	id_cotacao_empresa int8 NOT NULL,
	id_item int8 NOT NULL,
	CONSTRAINT cotacao_empresa_item_pkey PRIMARY KEY (id)
);
