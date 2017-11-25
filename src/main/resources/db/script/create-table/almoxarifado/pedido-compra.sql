CREATE TABLE almoxarifado.pedido_compra (
	id int8 NOT NULL,
	data_cadastro timestamp NULL,
	numero_pedido int4 NULL,
	previsao date NULL,
	empreendimento_id int8 NULL,
	fornecedor_id int8 NULL,
	usuario_cadastro int8 NULL,
	CONSTRAINT pedido_compra_pkey PRIMARY KEY (id),
	CONSTRAINT fk2ot6wgakmwkla0yo8dj0gnti FOREIGN KEY (usuario_cadastro) REFERENCES communs.usuario(id),
	CONSTRAINT fkjkayk8gmdo1hmmnjt42si5lat FOREIGN KEY (fornecedor_id) REFERENCES almoxarifado.fornecedor(id),
	CONSTRAINT fkl9l3e7h7xdar0er8547kgj02c FOREIGN KEY (empreendimento_id) REFERENCES communs.empreendimento(id)
)