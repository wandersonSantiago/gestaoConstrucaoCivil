CREATE TABLE almoxarifado.nota_fiscal_produto (
	id int8 NOT NULL,
	id_fornecedor int8 NOT NULL,
	id_nota_fiscal int8 NULL,
	CONSTRAINT nota_fiscal_produto_pkey PRIMARY KEY (id)
	 
)