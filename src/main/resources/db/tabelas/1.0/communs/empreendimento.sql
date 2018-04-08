CREATE TABLE communs.empreendimento (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	data_abertura date NULL,
	data_fechamento date NULL,
	descricao varchar(50) NOT NULL,
	porcentagem float8 NOT NULL,
	tipo_empreendimento varchar(255) NULL,
	valor_maximo_gastar float8 NOT NULL,
	valores_gastos float8 NOT NULL,
	id_endereco_empreendimento int8 NOT NULL,
	id_engenheiro_responsavel_funcionario int8 NULL,
	id_engenheiro_responsavel_terceiro int8 NULL,
	CONSTRAINT empreendimento_pkey PRIMARY KEY (id)
	 
)