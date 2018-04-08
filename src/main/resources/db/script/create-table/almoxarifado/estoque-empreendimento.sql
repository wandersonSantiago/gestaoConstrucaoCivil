CREATE TABLE almoxarifado.estoque_empreendimento (
	id int8 NOT NULL DEFAULT nextval('almoxarifado.estoque_empreendimento_id_seq'::regclass),
	localizacao varchar(255) NULL,
	quantidade int4 NOT NULL,
	quantidade_maxima int4 NOT NULL,
	quantidade_minima int4 NOT NULL,
	id_empreendimento int8 NULL,
	id_produto int8 NULL,
	CONSTRAINT estoque_empreendimento_pkey PRIMARY KEY (id),
	CONSTRAINT fkr2qr2j67m9w1g2oavyw07tg0e FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id),
	CONSTRAINT fkswjr3wknleay6troekt0melr8 FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id)
)