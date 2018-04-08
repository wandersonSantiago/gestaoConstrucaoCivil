CREATE TABLE almoxarifado.transferencia (
	id int8 NOT NULL,
	status_transferencia varchar(255) NULL,
	id_empreendimento_destino int8 NOT NULL,
	id_nota_fiscal int8 NULL,
	CONSTRAINT transferencia_pkey PRIMARY KEY (id)
)