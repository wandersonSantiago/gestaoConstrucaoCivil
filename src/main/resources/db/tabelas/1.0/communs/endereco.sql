CREATE TABLE communs.endereco (
	id int8 NOT NULL,
	bairro varchar(50) NOT NULL,
	cep varchar(9) NOT NULL,
	complemento varchar(50) NULL,
	localidade varchar(50) NOT NULL,
	logradouro varchar(50) NOT NULL,
	numero int4 NOT NULL,
	uf varchar(255) NOT NULL,
	CONSTRAINT endereco_pkey PRIMARY KEY (id)
)