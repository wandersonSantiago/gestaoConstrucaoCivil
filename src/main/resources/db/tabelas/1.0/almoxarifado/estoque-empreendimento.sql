CREATE TABLE almoxarifado.estoque_empreendimento (
	id int8 NOT NULL,
	localizacao varchar(255) NULL,
	quantidade int4 NOT NULL,
	quantidade_maxima int4 NOT NULL,
	quantidade_minima int4 NOT NULL,
	id_empreendimento int8 NULL,
	id_produto int8 NULL,
	CONSTRAINT estoque_empreendimento_pkey PRIMARY KEY (id)
	 
);
