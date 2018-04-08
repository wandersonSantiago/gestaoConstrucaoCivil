CREATE TABLE almoxarifado.fornecedor (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	contato varchar(50) NOT NULL,
	observacao varchar(50) NULL,
	id_dado_empresa int8 NOT NULL,
	CONSTRAINT fornecedor_pkey PRIMARY KEY (id),
	CONSTRAINT fkkx1g1djx4amdhnwpcmo8w65xc FOREIGN KEY (id_dado_empresa) REFERENCES communs.dado_empresa(id)
)