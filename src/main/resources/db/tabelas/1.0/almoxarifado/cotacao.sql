CREATE TABLE almoxarifado.cotacao (
	id int8 NOT NULL,
	data_criacao date NULL,
	data_fechamento date NULL,
	data_limite date NULL,
	status_cotacao varchar(255) NULL,
	tema varchar(255) NOT NULL,
	id_empreendimento int8 NOT NULL,
	id_usuario_cadastro int8 NULL,
	CONSTRAINT cotacao_pkey PRIMARY KEY (id)
	)