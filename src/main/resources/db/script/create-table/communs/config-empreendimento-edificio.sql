CREATE TABLE communs.config_empreendimento_edificio (
	quantidade_andar_por_torre int4 NOT NULL,
	quantidade_apartamento_por_andar int4 NOT NULL,
	quantidade_torre int4 NOT NULL,
	id int8 NOT NULL,
	CONSTRAINT config_empreendimento_edificio_pkey PRIMARY KEY (id),
	CONSTRAINT fkjywi6xuchiovb6amlgkcm63eb FOREIGN KEY (id) REFERENCES communs.config_empreendimento(id)
)