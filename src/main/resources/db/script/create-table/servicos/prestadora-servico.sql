CREATE TABLE servicos.prestadora_servico (
	id int8 NOT NULL,
	id_dado_empresa int8 NOT NULL,
	CONSTRAINT prestadora_servico_pkey PRIMARY KEY (id),
	CONSTRAINT fksj2d5rr37jxw51tg210o2gsv1 FOREIGN KEY (id_dado_empresa) REFERENCES communs.dado_empresa(id)
)