CREATE TABLE almoxarifado.cotacao (
	id int8 NOT NULL,
	data_criacao date NULL,
	data_fechamento date NULL,
	data_limite date NULL,
	status_cotacao varchar(255) NULL,
	tema varchar(255) NOT NULL,
	id_empreendimento int8 NOT NULL,
	id_usuario_cadastro int8 NULL,
	CONSTRAINT cotacao_pkey PRIMARY KEY (id),
	CONSTRAINT fk3uf322eo2e48otekwukhmkv5d FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id),
	CONSTRAINT fkqw2qn9gar5vfuomoa82wpryxt FOREIGN KEY (id_usuario_cadastro) REFERENCES communs.usuario(id)
)