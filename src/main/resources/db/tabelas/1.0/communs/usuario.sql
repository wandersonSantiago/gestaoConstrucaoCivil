CREATE TABLE communs.usuario (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	data_cadastro date NULL,
	email varchar(40) NULL,
	login varchar(15) NOT NULL,
	nome varchar(50) NOT NULL,
	senha varchar(256) NOT NULL,
	id_empreendimento int8 NULL,
	id_funcionario int8 NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id)

)