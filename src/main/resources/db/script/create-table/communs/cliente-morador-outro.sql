CREATE TABLE communs.cliente_morador_outros (
	descricao varchar(255) NOT NULL,
	id int8 NOT NULL,
	CONSTRAINT cliente_morador_outros_pkey PRIMARY KEY (id),
	CONSTRAINT fkrp4jl7iqgevpy204xsjm8sgbs FOREIGN KEY (id) REFERENCES communs.cliente_morador(id)
)