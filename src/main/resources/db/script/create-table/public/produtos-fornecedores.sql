CREATE TABLE public.produtos_fornecedores (
	id_produto int8 NOT NULL,
	id_fornecedor int8 NOT NULL,
	CONSTRAINT fk2niaesbdt5ba2w9n9car6o2o FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id),
	CONSTRAINT fkw1avxunbbd3633ftj0aj3q6r FOREIGN KEY (id_fornecedor) REFERENCES almoxarifado.fornecedor(id)
)