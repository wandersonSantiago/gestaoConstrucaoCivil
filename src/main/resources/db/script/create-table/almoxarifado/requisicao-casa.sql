CREATE TABLE almoxarifado.requisicao_casa (
	id int8 NOT NULL,
	id_informacao_requisicao int8 NULL,
	CONSTRAINT requisicao_casa_pkey PRIMARY KEY (id),
	CONSTRAINT fkakd5om8pumpgdvpmspik8runc FOREIGN KEY (id_informacao_requisicao) REFERENCES almoxarifado.informacao_requisicao(id)
)