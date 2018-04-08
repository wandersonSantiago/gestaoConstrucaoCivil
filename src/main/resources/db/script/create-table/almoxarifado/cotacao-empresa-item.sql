CREATE TABLE almoxarifado.cotacao_empresa_item (
	id int8 NOT NULL,
	observacao varchar(255) NULL,
	status varchar(255) NULL,
	valor_unitario float8 NOT NULL,
	id_cotacao_empresa int8 NOT NULL,
	id_item int8 NOT NULL,
	CONSTRAINT cotacao_empresa_item_pkey PRIMARY KEY (id),
	CONSTRAINT fkfnjvdjh2ot3sl6t9n8kh49o41 FOREIGN KEY (id_item) REFERENCES almoxarifado.cotacao_item(id),
	CONSTRAINT fki0kc0ffk28aqsflohwr6pds3g FOREIGN KEY (id_cotacao_empresa) REFERENCES almoxarifado.cotacao_empresa(id)
)