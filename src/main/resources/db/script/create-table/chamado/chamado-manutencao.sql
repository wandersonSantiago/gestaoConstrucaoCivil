CREATE TABLE chamado.chamado_manutencao (
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
	CONSTRAINT chamado_manutencao_pkey PRIMARY KEY (id),
	CONSTRAINT fk_amd9cv3wpiqsa8d0ya68mggxt FOREIGN KEY (id_usuario_solicitante) REFERENCES communs.usuario(id),
	CONSTRAINT fk_hrnhw0vphbt9pndx33oufwkmj FOREIGN KEY (id_usuario_atendente) REFERENCES communs.usuario(id),
	CONSTRAINT fk_iq5ovrup6fjjc32lilgfc3mir FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id)
)