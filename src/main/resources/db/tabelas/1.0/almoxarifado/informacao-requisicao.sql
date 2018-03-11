CREATE TABLE almoxarifado.informacao_requisicao (
	id int8 NOT NULL,
	data_criacao date NULL,
	data_saida date NULL,
	numero_requisicao int4 NULL,
	status_requisicao varchar(255) NULL,
	id_empreendimento int8 NULL,
	id_usuario_cadastro int8 NULL,
	CONSTRAINT informacao_requisicao_pkey PRIMARY KEY (id)
	 
)