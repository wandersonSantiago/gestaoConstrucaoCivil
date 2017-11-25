CREATE TABLE almoxarifado.nota_fiscal_produto (
	id int8 NOT NULL,
	id_fornecedor int8 NOT NULL,
	id_nota_fiscal int8 NULL,
	CONSTRAINT nota_fiscal_produto_pkey PRIMARY KEY (id),
	CONSTRAINT fk7e6khna2xrye1yi2r88qrb1ft FOREIGN KEY (id_nota_fiscal) REFERENCES almoxarifado.nota_fiscal(id),
	CONSTRAINT fkm6w4dn7y218lglboggxpitgw7 FOREIGN KEY (id_fornecedor) REFERENCES almoxarifado.fornecedor(id)
)