CREATE TABLE communs.funcionario (
	ativo bool NOT NULL,
	carteira_trabalho int4 NOT NULL,
	crea int4 NULL,
	data_admissao date NULL,
	id int8 NOT NULL,
	id_cargo int8 NULL,
	id_empreendimento int8 NULL,
	CONSTRAINT funcionario_pkey PRIMARY KEY (id),
	CONSTRAINT fk92othhhe25gno2qtus5woc6ys FOREIGN KEY (id_cargo) REFERENCES communs.cargo(id),
	CONSTRAINT fkjs6iadl1wroqdibh61lp5dhnb FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id),
	CONSTRAINT fkpjvvctvfjm69dvye3aea5k56f FOREIGN KEY (id) REFERENCES communs.pessoa(id)
)