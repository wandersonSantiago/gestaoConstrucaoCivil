CREATE TABLE servicos.ocorrencia_servico (
	id int8 NOT NULL,
	arquivo int2 NULL,
	data timestamp NULL,
	ocorrencia varchar(255) NULL,
	id_servico_empresa int8 NULL,
	id_usuario int8 NULL,
	CONSTRAINT ocorrencia_servico_pkey PRIMARY KEY (id),
	CONSTRAINT fkaiap4d7xj3lis6ypy84enu8lk FOREIGN KEY (id_servico_empresa) REFERENCES servicos.servico_empresa(id),
	CONSTRAINT fkfqkdofn6un2nd2h3wsqw03286 FOREIGN KEY (id_usuario) REFERENCES communs.usuario(id)
)