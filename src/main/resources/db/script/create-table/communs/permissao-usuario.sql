CREATE TABLE communs.permissao_usuario (
	id int8 NOT NULL,
	permissao_id int8 NULL,
	usuario_id int8 NULL,
	CONSTRAINT permissao_usuario_pkey PRIMARY KEY (id),
	CONSTRAINT fk3b2s9hbeysqtbkwrwbus7cuku FOREIGN KEY (usuario_id) REFERENCES communs.usuario(id),
	CONSTRAINT fkggkpksqth0nps65cnfjk6vhok FOREIGN KEY (permissao_id) REFERENCES communs.permissao(id)
)