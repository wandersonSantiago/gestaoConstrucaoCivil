CREATE TABLE almoxarifado.requisicao_outros (
	id int8 NOT NULL,
	id_informacao_requisicao int8 NULL,
	CONSTRAINT requisicao_outros_pkey PRIMARY KEY (id),
	CONSTRAINT fkaxv3nm4o74mgq60jmr08y4ep5 FOREIGN KEY (id_informacao_requisicao) REFERENCES almoxarifado.informacao_requisicao(id)
)