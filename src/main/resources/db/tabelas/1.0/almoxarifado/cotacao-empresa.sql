CREATE TABLE almoxarifado.cotacao_empresa (
	id int8 NOT NULL,
	ganhou bool NULL,
	id_cotacao int8 NOT NULL,
	id_fornecedor int8 NOT NULL,
	CONSTRAINT cotacao_empresa_pkey PRIMARY KEY (id)
	 
)