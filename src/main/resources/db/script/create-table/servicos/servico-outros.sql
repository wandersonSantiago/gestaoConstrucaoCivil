CREATE TABLE servicos.servico_outros (
	descricao varchar(255) NULL,
	id int8 NOT NULL,
	CONSTRAINT servico_outros_pkey PRIMARY KEY (id),
	CONSTRAINT fkpi7uofqcs9nl5p396fjjiv3cq FOREIGN KEY (id) REFERENCES servicos.servico_empresa(id)
)