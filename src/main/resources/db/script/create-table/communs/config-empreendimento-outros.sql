CREATE TABLE communs.config_empreendimento_outros (
	id int8 NOT NULL,
	descricao varchar(250) NOT NULL,
	id_empreendimento int8 NULL,
	CONSTRAINT config_empreendimento_outros_pkey PRIMARY KEY (id),
	CONSTRAINT fk4kokib8th74e8kid2l4p7ugp3 FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id)
)