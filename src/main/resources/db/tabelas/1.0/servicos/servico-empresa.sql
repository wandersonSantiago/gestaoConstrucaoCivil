CREATE TABLE servicos.servico_empresa (
	id int8 NOT NULL,
	data_cadastro timestamp NULL,
	data_encerramento_servico timestamp NULL,
	data_fechamento timestamp NULL,
	data_pagamento timestamp NULL,
	observacao varchar(255) NULL,
	porcentagem float8 NULL,
	valor_adicional float8 NULL,
	valor_desconto float8 NULL,
	valor_pacote_servico float8 NULL,
	valor_total_pago float8 NULL,
	id_empreendimento int8 NOT NULL,
	id_pacote_servico int8 NOT NULL,
	id_prestadora_servico int8 NOT NULL,
	id_usuario int8 NULL,
	CONSTRAINT servico_empresa_pkey PRIMARY KEY (id)
	 
)