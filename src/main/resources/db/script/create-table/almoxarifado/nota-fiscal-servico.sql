CREATE TABLE almoxarifado.nota_fiscal_servico (
	id int8 NOT NULL,
	id_nota_fiscal int8 NULL,
	CONSTRAINT nota_fiscal_servico_pkey PRIMARY KEY (id),
	CONSTRAINT fkl2wvfnr1180ylxntkmbkm8yxm FOREIGN KEY (id_nota_fiscal) REFERENCES almoxarifado.nota_fiscal(id)
)