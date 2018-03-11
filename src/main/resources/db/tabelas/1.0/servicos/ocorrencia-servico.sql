CREATE TABLE servicos.ocorrencia_servico (
	id int8 NOT NULL,
	arquivo int2 NULL,
	data timestamp NULL,
	ocorrencia varchar(255) NULL,
	id_servico_empresa int8 NULL,
	id_usuario int8 NULL,
	CONSTRAINT ocorrencia_servico_pkey PRIMARY KEY (id)
	 
	 
)