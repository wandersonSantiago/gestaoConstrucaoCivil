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
	CONSTRAINT empreendimento_pkey PRIMARY KEY (id),
	CONSTRAINT fkboocxk7l4ksh0dworthd9yii0 FOREIGN KEY (id_engenheiro_responsavel_funcionario) REFERENCES communs.funcionario(id),
	CONSTRAINT fkepgj0up27svkjn18usy5rhbad FOREIGN KEY (id_endereco_empreendimento) REFERENCES communs.endereco(id),
	CONSTRAINT fkhu4qce6ei6188u7dauligvx8h FOREIGN KEY (id_engenheiro_responsavel_terceiro) REFERENCES servicos.prestadora_servico(id)
)