CREATE TABLE almoxarifado.nota_fiscal_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	valor_total float8 NOT NULL,
	id_nota_fiscal int8 NULL,
	CONSTRAINT nota_fiscal_item_pkey PRIMARY KEY (id)
 
)