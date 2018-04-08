CREATE TABLE communs.dado_empresa (
	id int8 NOT NULL,
	cnpj varchar(20) NOT NULL,
	email varchar(40) NULL,
	inscricao_estadual varchar(20) NOT NULL,
	nome_fantasia varchar(50) NOT NULL,
	razao_social varchar(50) NOT NULL,
	telefone varchar(15) NOT NULL,
	uf_ie varchar(255) NULL,
	id_endereco int8 NOT NULL,
	CONSTRAINT dado_empresa_pkey PRIMARY KEY (id)
)