CREATE TABLE communs.config_empreendimento_casa (
	quantidade_andar_por_casa int4 NOT NULL,
	quantidade_casa int4 NOT NULL,
	id int8 NOT NULL,
	CONSTRAINT config_empreendimento_casa_pkey PRIMARY KEY (id),
	CONSTRAINT fk2hqb3gxibukkw9bjqof1d12d2 FOREIGN KEY (id) REFERENCES communs.config_empreendimento(id)
)