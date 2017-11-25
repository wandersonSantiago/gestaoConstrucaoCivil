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
	CONSTRAINT chamado_ti_pkey PRIMARY KEY (id),
	CONSTRAINT fk_4710yn7v1q4qlgtuhob4eh1a0 FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id),
	CONSTRAINT fk_dr4d1ayiir1ibdm4l6ndyy0n8 FOREIGN KEY (id_usuario_atendente) REFERENCES communs.usuario(id),
	CONSTRAINT fk_e66owwifteo1jx0lxl8e230d9 FOREIGN KEY (id_usuario_solicitante) REFERENCES communs.usuario(id)
)