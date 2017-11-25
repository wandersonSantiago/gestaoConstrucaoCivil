CREATE TABLE almoxarifado.transferencia (
	id int8 NOT NULL,
	status_transferencia varchar(255) NULL,
	id_empreendimento_destino int8 NOT NULL,
	id_nota_fiscal int8 NULL,
	CONSTRAINT transferencia_pkey PRIMARY KEY (id),
	CONSTRAINT fkbjfjb49kw03pkms53g9y6ftbk FOREIGN KEY (id_empreendimento_destino) REFERENCES communs.empreendimento(id),
	CONSTRAINT fksxl1n2yy9y4qilx9llcwtq3l1 FOREIGN KEY (id_nota_fiscal) REFERENCES almoxarifado.nota_fiscal(id)
)