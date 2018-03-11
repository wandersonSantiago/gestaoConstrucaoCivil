CREATE TABLE almoxarifado.pedido_compra (
	id int8 NOT NULL,
	data_cadastro timestamp NULL,
	numero_pedido int4 NULL,
	previsao date NULL,
	empreendimento_id int8 NULL,
	fornecedor_id int8 NULL,
	usuario_cadastro int8 NULL,
	CONSTRAINT pedido_compra_pkey PRIMARY KEY (id)
)