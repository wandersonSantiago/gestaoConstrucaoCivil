CREATE TABLE communs.funcionario (
	ativo bool NOT NULL,
	carteira_trabalho int4 NOT NULL,
	crea int4 NULL,
	data_admissao date NULL,
	id int8 NOT NULL,
	id_cargo int8 NULL,
	id_empreendimento int8 NULL,
	CONSTRAINT funcionario_pkey PRIMARY KEY (id)
 
	 
)