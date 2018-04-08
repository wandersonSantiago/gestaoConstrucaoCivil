CREATE TABLE communs.cliente_morador (
	id int8 NOT NULL,
	cpf varchar(255) NULL,
	data_nascimento date NULL,
	email varchar(255) NULL,
	nome_completo varchar(50) NOT NULL,
	telefone_celular varchar(15) NOT NULL,
	telefone_fixo varchar(15) NOT NULL,
	id_empreendimento int8 NULL,
	id_usuario int8 NULL,
	CONSTRAINT cliente_morador_pkey PRIMARY KEY (id),
	CONSTRAINT fk50ck7ntnxk6vikumnokvpbapt FOREIGN KEY (id_usuario) REFERENCES communs.usuario(id),
	CONSTRAINT fkccdqopx6bey5um5abshj9hae5 FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id)
)