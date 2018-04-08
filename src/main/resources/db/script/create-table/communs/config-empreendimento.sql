CREATE TABLE communs.config_empreendimento (
	id int8 NOT NULL,
	tipo int4 NULL,
	id_empreendimento int8 NULL,
	CONSTRAINT config_empreendimento_pkey PRIMARY KEY (id),
	CONSTRAINT fk42d3wrr6atitet5hrtqs07org FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id)
)