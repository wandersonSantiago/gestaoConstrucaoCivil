CREATE TABLE chamado.chamado_ti (
	id int8 NOT NULL,
	data_abertura timestamp NULL,
	data_fechamento timestamp NULL,
	lido bool NULL,
	prioridade varchar(255) NULL,
	silenciar bool NULL,
	status varchar(255) NULL,
	titulo varchar(255) NULL,
	id_empreendimento int8 NULL,
	id_usuario_atendente int8 NULL,
	id_usuario_solicitante int8 NULL,
	descricao_servico varchar(255) NULL,
	tipo_equipamento varchar(255) NULL,
	CONSTRAINT chamado_ti_pkey PRIMARY KEY (id)
	 
)