CREATE SCHEMA almoxarifado; 
CREATE SCHEMA servicos; 
CREATE SCHEMA communs; 
CREATE SCHEMA chamado; 

CREATE SEQUENCE almoxarifado.cotacao_empresa_id_seq;
CREATE SEQUENCE almoxarifado.cotacao_empresa_item_id_seq;
CREATE SEQUENCE almoxarifado.cotacao_id_seq;
CREATE SEQUENCE almoxarifado.estoque_empreendimento_id_seq;
CREATE SEQUENCE almoxarifado.fabricante_id_seq;
CREATE SEQUENCE almoxarifado.fornecedor_id_seq;
CREATE SEQUENCE almoxarifado.item_cotacao_id_seq;
CREATE SEQUENCE almoxarifado.item_id_seq;
CREATE SEQUENCE almoxarifado.nota_fiscal_id_seq;
CREATE SEQUENCE almoxarifado.nota_fiscal_produto_id_seq;
CREATE SEQUENCE almoxarifado.nota_fiscal_servico_id_seq;
CREATE SEQUENCE almoxarifado.pedido_compra_id_seq;
CREATE SEQUENCE almoxarifado.produto_id_seq;
CREATE SEQUENCE almoxarifado.requisicao_id_seq;
CREATE SEQUENCE almoxarifado.tipo_produto_id_seq;
CREATE SEQUENCE almoxarifado.transferencia_id_seq;
CREATE SEQUENCE chamado.chamado_id_seq;
CREATE SEQUENCE chamado.mensagem_id_seq;
CREATE SEQUENCE servicos.ocorrencia_servico_id_seq;
CREATE SEQUENCE servicos.pacote_servico_id_seq;
CREATE SEQUENCE servicos.prestadora_servico_id_seq;
CREATE SEQUENCE servicos.servico_empresa_id_seq; 
CREATE SEQUENCE communs.aluguel_equipamento_id_seq;
CREATE SEQUENCE communs.area_produto_id_seq;
CREATE SEQUENCE communs.arquivo_produto_id_seq;
CREATE SEQUENCE communs.cargo_id_seq;
CREATE SEQUENCE communs.categoria_id_seq;
CREATE SEQUENCE communs.morador_id_seq;
CREATE SEQUENCE communs.dado_empresa_id_seq;
CREATE SEQUENCE communs.empreendimento_id_seq;
CREATE SEQUENCE communs.empresa_contratante_id_seq;
CREATE SEQUENCE communs.endereco_id_seq;
CREATE SEQUENCE communs.permissao_id_seq;
CREATE SEQUENCE communs.permissao_usuario_id_seq;
CREATE SEQUENCE communs.pessoa_id_seq;
CREATE SEQUENCE communs.usuario_id_seq;
CREATE SEQUENCE communs.estrutura_id_seq;

--Tables Almoxarifado
CREATE TABLE almoxarifado.pedido_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	id_pedido_compra int8 NULL,
	CONSTRAINT pedido_item_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.nota_fiscal (
	id int8 NOT NULL,
	data_nota date NULL,
	data_vencimento date NULL,
	numero int8 NOT NULL,
	observacao varchar(255) NULL,
	situacao varchar(255) NULL,
	tipo_nota varchar(255) NULL,
	valor_total float8 NOT NULL,
	id_empreendimento int8 NULL,
	CONSTRAINT nota_fiscal_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.nota_fiscal_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	valor_total float8 NOT NULL,
	id_nota_fiscal int8 NULL,
	CONSTRAINT nota_fiscal_item_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.requisicao (
	id int8 NOT NULL,
	data_criacao date NULL,
	data_saida date NULL,
	numero_requisicao int4 NULL,
	status_requisicao varchar(255) NULL,
	id_empreendimento int8 NULL,
	CONSTRAINT requisicao_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.requisicao_item(
	id int8 NOT NULL,
	quantidade int4 not null,
 	valor_unitario float8 not null,
	id_produto int8 not null,
	id_requisicao int8,
	CONSTRAINT requisicao_item_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.nota_fiscal_produto (
	id int8 NOT NULL,
	id_fornecedor int8 NOT NULL,
	id_nota_fiscal int8 NULL,
	CONSTRAINT nota_fiscal_produto_pkey PRIMARY KEY (id)
	 
);
CREATE TABLE almoxarifado.nota_fiscal_servico (
	id int8 NOT NULL,
	id_nota_fiscal int8 NULL,
	CONSTRAINT nota_fiscal_servico_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.pedido_compra (
	id int8 NOT NULL,
	data_cadastro timestamp NULL,
	numero_pedido int4 NULL,
	previsao date NULL,
	empreendimento_id int8 NULL,
	fornecedor_id int8 NULL,
	usuario_cadastro int8 NULL,
	CONSTRAINT pedido_compra_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.produto (
	id int8 NOT NULL,
	ativo bool NULL,
	codigo int4 NULL,
	codigo_barra varchar(50) NOT NULL,
	descricao varchar(50) NOT NULL,
	unidade_medida varchar(255) NULL,
	id_categoria int8 NULL,
	id_fabricante int8 NULL,
	id_tipo int8 NULL,
	CONSTRAINT produto_pkey PRIMARY KEY (id)
);
ALTER TABLE almoxarifado.produto ADD CONSTRAINT uk_a32tv48ww0lvru07p7se4c15o UNIQUE (codigo);
ALTER TABLE almoxarifado.produto ADD CONSTRAINT uk_ch9vhi0a5s30o477fb50ook3 UNIQUE (codigo_barra);

CREATE TABLE almoxarifado.fornecedor (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	contato varchar(50) NOT NULL,
	observacao varchar(50) NULL,
	id_dado_empresa int8 NOT NULL,
	CONSTRAINT fornecedor_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.produtos_fornecedores (
	id_produto int8 NOT NULL,
	id_fornecedor int8 NOT NULL
);
CREATE TABLE almoxarifado.fabricante (
	id int8 NOT NULL,
	id_dado_empresa int8 NOT NULL,
	CONSTRAINT fabricante_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.estoque_empreendimento (
	id int8 NOT NULL,
	localizacao varchar(255) NULL,
	quantidade int4 NOT NULL,
	quantidade_maxima int4 NOT NULL,
	quantidade_minima int4 NOT NULL,
	id_empreendimento int8 NULL,
	id_produto int8 NULL,
	CONSTRAINT estoque_empreendimento_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.tipo_produto (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	descricao varchar(30) NOT NULL,
	CONSTRAINT tipo_produto_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.transferencia_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	id_transferencia int8 NULL,
	CONSTRAINT transferencia_item_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.transferencia (
	id int8 NOT NULL,
	status_transferencia varchar(255) NULL,
	id_empreendimento_destino int8 NOT NULL,
	id_nota_fiscal int8 NULL,
	CONSTRAINT transferencia_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.cotacao_empresa_item (
	id int8 NOT NULL,
	observacao varchar(255) NULL,
	status varchar(255) NULL,
	valor_unitario float8 NOT NULL,
	id_cotacao_empresa int8 NOT NULL,
	id_item int8 NOT NULL,
	CONSTRAINT cotacao_empresa_item_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.cotacao (
	id int8 NOT NULL,
	data_criacao date NULL,
	data_fechamento date NULL,
	data_limite date NULL,
	status_cotacao varchar(255) NULL,
	tema varchar(255) NOT NULL,
	id_empreendimento int8 NOT NULL,
	CONSTRAINT cotacao_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.cotacao_item (
	id int8 NOT NULL,
	descricao varchar(255) NOT NULL,
	quantidade int4 NOT NULL,
	id_cotacao int8 NULL,
	CONSTRAINT cotacao_item_pkey PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.cotacao_empresa (
	id int8 NOT NULL,
	ganhou bool NULL,
	id_cotacao int8 NOT NULL,
	id_fornecedor int8 NOT NULL,
	CONSTRAINT cotacao_empresa_pkey PRIMARY KEY (id)
);
 
--Tables Chamado
CREATE TABLE chamado.chamado_ti (
	id int8 NOT NULL,
	data_abertura timestamp NULL,
	data_fechamento timestamp NULL,
	lido bool NULL,
	prioridade varchar(255) NULL,
	silenciar bool NULL,
	status varchar(255) NULL,
	titulo varchar(255) NULL,
	id_empreendimento int8 NULL,
	id_usuario_atendente int8 NULL,
	id_usuario_solicitante int8 NULL,
	descricao_servico varchar(255) NULL,
	tipo_equipamento varchar(255) NULL,
	CONSTRAINT chamado_ti_pkey PRIMARY KEY (id)
);
CREATE TABLE chamado.chamado_manutencao (
	id int8 NOT NULL,
	data_abertura timestamp NULL,
	data_fechamento timestamp NULL,
	lido bool NULL,
	prioridade varchar(255) NULL,
	silenciar bool NULL,
	status varchar(255) NULL,
	titulo varchar(255) NULL,
	id_empreendimento int8 NULL,
	id_usuario_atendente int8 NULL,
	id_usuario_solicitante int8 NULL,
	descricao_servico varchar(255) NULL,
	CONSTRAINT chamado_manutencao_pkey PRIMARY KEY (id)
);
CREATE TABLE chamado.mensagem (
	id int8 NOT NULL,
	arquivo int2 NULL,
	data timestamp NULL,
	texto varchar(255) NULL,
	id_chamado int8 NULL,
	id_usuario int8 NULL,
	CONSTRAINT mensagem_pkey PRIMARY KEY (id)
);

--- tables servicos
CREATE TABLE servicos.ocorrencia_servico (
	id int8 NOT NULL,
	arquivo int2 NULL,
	data timestamp NULL,
	ocorrencia varchar(255) NULL,
	id_servico_empresa int8 NULL,
	id_usuario int8 NULL,
	CONSTRAINT ocorrencia_servico_pkey PRIMARY KEY (id)
);
CREATE TABLE servicos.pacote_servico (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	codigo int4 NOT NULL,
	criterio_servico varchar(250) NOT NULL,
	descricao varchar(50) NOT NULL,
	servicos_atribuidos varchar(250) NOT NULL,
	valor float8 NOT NULL,
	id_categoria int8 NULL,
	CONSTRAINT pacote_servico_pkey PRIMARY KEY (id)
);
CREATE TABLE servicos.prestadora_servico (
	id int8 NOT NULL,
	id_dado_empresa int8 NOT NULL,
	CONSTRAINT prestadora_servico_pkey PRIMARY KEY (id)
);
 
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
);

--tables Communs
CREATE TABLE communs.estrutura(
    id bigint NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default",
    id_empreendimento bigint,
    raiz_id bigint,
    CONSTRAINT estrutura_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.permissao (
	id int8 NOT NULL,
	descricao varchar(255) NULL,
	rol varchar(255) NULL,
	tipo_modulo varchar(255) NULL,
	CONSTRAINT permissao_pkey PRIMARY KEY (id)
);
 CREATE TABLE communs.usuario_permissoes (
	id int8 NOT NULL,
	permissao_id int8 NULL,
	usuario_id int8 NULL,
	CONSTRAINT usuario_permissoes_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.funcionario (
	ativo bool NOT NULL,
	carteira_trabalho int4 NOT NULL,
	crea int4 NULL,
	data_admissao date NULL,
	id int8 NOT NULL,
	id_cargo int8 NULL,
	id_empreendimento int8 NULL,
	CONSTRAINT funcionario_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.endereco (
	id int8 NOT NULL,
	bairro varchar(50) NOT NULL,
	cep varchar(9) NOT NULL,
	complemento varchar(50) NULL,
	localidade varchar(50) NOT NULL,
	logradouro varchar(50) NOT NULL,
	numero int4 NOT NULL,
	uf varchar(255) NOT NULL,
	CONSTRAINT endereco_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.empresa_contratante (
	id int8 NOT NULL,
	id_dado_empresa int8 NOT NULL,
	CONSTRAINT empresa_contratante_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.empreendimento (
	id int8 NOT NULL,
	data_abertura date NULL,
	data_fechamento date NULL,
	descricao varchar(50) NOT NULL,
	porcentagem float8 NOT NULL,
	tipo_empreendimento varchar(255) NULL,
	status varchar(150) NULL,
	telefone varchar(100) NULL,
	matriz_id int8 NULL,
	valor_maximo_gastar float8 NOT NULL,
	valores_gastos float8 NOT NULL,
	id_endereco int8 NOT NULL,
	id_engenheiro_responsavel_funcionario int8 NULL,
	id_engenheiro_responsavel_terceiro int8 NULL,
	CONSTRAINT empreendimento_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.dado_empresa (
	id int8 NOT NULL,
	cnpj varchar(20) NOT NULL,
	email varchar(40) NULL,
	inscricao_estadual varchar(20) NOT NULL,
	nome_fantasia varchar(50) NOT NULL,
	razao_social varchar(50) NOT NULL,
	telefone varchar(15) NOT NULL,
	uf_ie varchar(255) NULL,
	id_endereco int8 NOT NULL,
	CONSTRAINT dado_empresa_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.usuario (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	data_cadastro date NULL,
	email varchar(40) NULL,
	login varchar(15) NOT NULL,
	nome varchar(50) NOT NULL,
	senha varchar(256) NOT NULL,
	status varchar(200) NULL,
	id_empreendimento int8 NULL,
	id_funcionario int8 NULL,
	caminho_foto varchar(256),
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.morador (
	id int8 NOT NULL,
	cpf varchar(255) NULL,
	data_nascimento date NULL,
	email varchar(255) NULL,
	nome_completo varchar(50) NOT NULL,
	telefone_celular varchar(15) NOT NULL,
	telefone_fixo varchar(15) NOT NULL,
	id_empreendimento int8 NULL,
	id_usuario int8 NULL,
	CONSTRAINT cliente_morador_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.categoria (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	descricao varchar(25) NOT NULL,
	tipo_categoria varchar(255) NOT NULL,
	CONSTRAINT categoria_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.cargo (
	id int8 NOT NULL,
	descricao varchar(25) NOT NULL,
	CONSTRAINT cargo_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.arquivo_upload (
	id int8 NOT NULL,
	file bytea NULL,
	proprietario_id int8 NULL,
	CONSTRAINT arquivo_upload_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.area_produto (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	descricao varchar(25) NOT NULL,
	CONSTRAINT area_produto_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.aluguel_equipamento (
	id int8 NOT NULL,
	nome_equipamento varchar(255) NULL,
	preco_locacao float8 NULL,
	valor_aquisicao float8 NULL,
	CONSTRAINT aluguel_equipamento_pkey PRIMARY KEY (id)
);
CREATE TABLE communs.pessoa (
	id int8 NOT NULL,
	cpf varchar(20) NOT NULL,
	data_nascimento date NULL,
	estado_civil varchar(255) NULL,
	idade int4 NOT NULL,
	nome_completo varchar(50) NOT NULL,
	rg varchar(20) NOT NULL,
	sexo varchar(255) NOT NULL,
	telefone_celular varchar(15) NOT NULL,
	telefone_fixo varchar(15) NOT NULL,
	id_endereco int8 NOT NULL,
	CONSTRAINT pessoa_pkey PRIMARY KEY (id),
	CONSTRAINT uk_97gwi9nbsq9hgi7xtclbp7p7d UNIQUE (rg),
	CONSTRAINT uk_nlwiu48rutiltbnjle59krljo UNIQUE (cpf)
);

--CONSTRAINT chamado
ALTER TABLE chamado.chamado_manutencao
    ADD CONSTRAINT fk_amd9cv3wpiqsa8d0ya68mggxt FOREIGN KEY (id_usuario_solicitante)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE chamado.chamado_manutencao
    ADD CONSTRAINT fk_hrnhw0vphbt9pndx33oufwkmj FOREIGN KEY (id_usuario_atendente)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE chamado.chamado_manutencao
    ADD CONSTRAINT fk_iq5ovrup6fjjc32lilgfc3mir FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE chamado.chamado_ti
    ADD CONSTRAINT fk_4710yn7v1q4qlgtuhob4eh1a0 FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE chamado.chamado_ti
    ADD CONSTRAINT fk_dr4d1ayiir1ibdm4l6ndyy0n8 FOREIGN KEY (id_usuario_atendente)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE chamado.chamado_ti
    ADD CONSTRAINT fk_e66owwifteo1jx0lxl8e230d9 FOREIGN KEY (id_usuario_solicitante)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE chamado.mensagem
    ADD CONSTRAINT fkqlofg9o3fjv2wk4r49o0abm5e FOREIGN KEY (id_usuario)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

 --CONSTRAINT communs
ALTER TABLE communs.morador
    ADD CONSTRAINT fk50ck7ntnxk6vikumnokvpbapt FOREIGN KEY (id_usuario)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.morador
    ADD CONSTRAINT fkccdqopx6bey5um5abshj9hae5 FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;   
ALTER TABLE communs.dado_empresa
    ADD CONSTRAINT fkqcsf4g30c72uprjlkaepvh3yt FOREIGN KEY (id_endereco)
    REFERENCES communs.endereco (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.empreendimento
    ADD CONSTRAINT fkepgj0up27svkjn18usy5rhbad FOREIGN KEY (id_endereco)
    REFERENCES communs.endereco (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.empresa_contratante
    ADD CONSTRAINT fkqqhnmknub93nv5jbspbmu8e2e FOREIGN KEY (id_dado_empresa)
    REFERENCES communs.dado_empresa (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.funcionario
    ADD CONSTRAINT fk92othhhe25gno2qtus5woc6ys FOREIGN KEY (id_cargo)
    REFERENCES communs.cargo (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.funcionario
    ADD CONSTRAINT fkjs6iadl1wroqdibh61lp5dhnb FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.funcionario
    ADD CONSTRAINT fkpjvvctvfjm69dvye3aea5k56f FOREIGN KEY (id)
    REFERENCES communs.pessoa (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.usuario_permissoes
    ADD CONSTRAINT fk3b2s9hbeysqtbkwrwbus7cuku FOREIGN KEY (usuario_id)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.usuario_permissoes
    ADD CONSTRAINT fkggkpksqth0nps65cnfjk6vhok FOREIGN KEY (permissao_id)
    REFERENCES communs.permissao (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.pessoa
    ADD CONSTRAINT fko2v72927b43j7ug0s8b97ymly FOREIGN KEY (id_endereco)
    REFERENCES communs.endereco (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.usuario
    ADD CONSTRAINT fkdc4aiu3biwhotpf49x1l34nlg FOREIGN KEY (id_funcionario)
    REFERENCES communs.funcionario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.usuario
    ADD CONSTRAINT fkrta44oqpseerxmyluygyy2xdk FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE communs.estrutura
    ADD CONSTRAINT fkrkye45rrfdxvetm0xjvwflfpf FOREIGN KEY (raiz_id)
    REFERENCES communs.estrutura (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
--CONSTRAINT Almoxarifado
ALTER TABLE almoxarifado.cotacao
    ADD CONSTRAINT fk3uf322eo2e48otekwukhmkv5d FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.cotacao_empresa
    ADD CONSTRAINT fk5ik5waqtlkyg3segkksv53k58 FOREIGN KEY (id_fornecedor)
    REFERENCES almoxarifado.fornecedor (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.cotacao_empresa
    ADD CONSTRAINT fkl6eecabk5w415c6vmm7gcepsd FOREIGN KEY (id_cotacao)
    REFERENCES almoxarifado.cotacao (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;            
ALTER TABLE almoxarifado.cotacao_empresa_item
    ADD CONSTRAINT fkfnjvdjh2ot3sl6t9n8kh49o41 FOREIGN KEY (id_item)
    REFERENCES almoxarifado.cotacao_item (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.cotacao_empresa_item
    ADD CONSTRAINT fki0kc0ffk28aqsflohwr6pds3g FOREIGN KEY (id_cotacao_empresa)
    REFERENCES almoxarifado.cotacao_empresa (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.cotacao_item
    ADD CONSTRAINT fk6dvm4jhys9bnsu2ht26blw11x FOREIGN KEY (id_cotacao)
    REFERENCES almoxarifado.cotacao (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.estoque_empreendimento
    ADD CONSTRAINT fkr2qr2j67m9w1g2oavyw07tg0e FOREIGN KEY (id_produto)
    REFERENCES almoxarifado.produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.estoque_empreendimento
    ADD CONSTRAINT fkswjr3wknleay6troekt0melr8 FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.fabricante
    ADD CONSTRAINT fkiphtgfonvn8ua5hxi9qb2wie8 FOREIGN KEY (id_dado_empresa)
    REFERENCES communs.dado_empresa (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.fornecedor
    ADD CONSTRAINT fkkx1g1djx4amdhnwpcmo8w65xc FOREIGN KEY (id_dado_empresa)
    REFERENCES communs.dado_empresa (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.requisicao
    ADD CONSTRAINT fkt68d7ovqioxsyoqgkphignglq FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.nota_fiscal
    ADD CONSTRAINT fkaop1c471vp2mvbmh5hnjy8qdf FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.nota_fiscal_item
    ADD CONSTRAINT fk_pkhw9mey9p4khg82dx5sggks8 FOREIGN KEY (id_produto)
    REFERENCES almoxarifado.produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.nota_fiscal_item
    ADD CONSTRAINT fklkb3jiffubruk1t3noslwn8sp FOREIGN KEY (id_nota_fiscal)
    REFERENCES almoxarifado.nota_fiscal_produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.nota_fiscal_produto
    ADD CONSTRAINT fk7e6khna2xrye1yi2r88qrb1ft FOREIGN KEY (id_nota_fiscal)
    REFERENCES almoxarifado.nota_fiscal (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.nota_fiscal_produto
    ADD CONSTRAINT fkm6w4dn7y218lglboggxpitgw7 FOREIGN KEY (id_fornecedor)
    REFERENCES almoxarifado.fornecedor (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.nota_fiscal_servico
    ADD CONSTRAINT fkl2wvfnr1180ylxntkmbkm8yxm FOREIGN KEY (id_nota_fiscal)
    REFERENCES almoxarifado.nota_fiscal (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.pedido_compra
    ADD CONSTRAINT fk2ot6wgakmwkla0yo8dj0gnti FOREIGN KEY (usuario_cadastro)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.pedido_compra
    ADD CONSTRAINT fkjkayk8gmdo1hmmnjt42si5lat FOREIGN KEY (fornecedor_id)
    REFERENCES almoxarifado.fornecedor (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.pedido_compra
    ADD CONSTRAINT fkl9l3e7h7xdar0er8547kgj02c FOREIGN KEY (empreendimento_id)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.pedido_item
    ADD CONSTRAINT fk_5guwi7yl0kr0jhrqqp8ciiy8l FOREIGN KEY (id_produto)
    REFERENCES almoxarifado.produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.pedido_item
    ADD CONSTRAINT fkpxbymligfrhcod4fwex0bf9pq FOREIGN KEY (id_pedido_compra)
    REFERENCES almoxarifado.pedido_compra (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.produto
    ADD CONSTRAINT fk6udkd7k6wb1ijuurahxkb9s71 FOREIGN KEY (id_tipo)
    REFERENCES almoxarifado.tipo_produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.produto
    ADD CONSTRAINT fkbb0k43mtsufg8bfhq0gyaxhhm FOREIGN KEY (id_categoria)
    REFERENCES communs.categoria (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.produto
    ADD CONSTRAINT fkmnq277j56jv03yc6qw8prvump FOREIGN KEY (id_fabricante)
    REFERENCES almoxarifado.fabricante (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.produtos_fornecedores
    ADD CONSTRAINT fk2niaesbdt5ba2w9n9car6o2o FOREIGN KEY (id_produto)
    REFERENCES almoxarifado.produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.produtos_fornecedores
    ADD CONSTRAINT fkw1avxunbbd3633ftj0aj3q6r FOREIGN KEY (id_fornecedor)
    REFERENCES almoxarifado.fornecedor (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.transferencia
    ADD CONSTRAINT fkbjfjb49kw03pkms53g9y6ftbk FOREIGN KEY (id_empreendimento_destino)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.transferencia
    ADD CONSTRAINT fksxl1n2yy9y4qilx9llcwtq3l1 FOREIGN KEY (id_nota_fiscal)
    REFERENCES almoxarifado.nota_fiscal (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.transferencia_item
    ADD CONSTRAINT fk4rrrdja6x52qugntd2i1911i9 FOREIGN KEY (id_transferencia)
    REFERENCES almoxarifado.transferencia (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE almoxarifado.transferencia_item
    ADD CONSTRAINT fk_o8og11ycjyq85w7wya8t8uuro FOREIGN KEY (id_produto)
    REFERENCES almoxarifado.produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

--CONSTRAINT Servicos
ALTER TABLE servicos.ocorrencia_servico
    ADD CONSTRAINT fkaiap4d7xj3lis6ypy84enu8lk FOREIGN KEY (id_servico_empresa)
    REFERENCES servicos.servico_empresa (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE servicos.ocorrencia_servico
    ADD CONSTRAINT fkfqkdofn6un2nd2h3wsqw03286 FOREIGN KEY (id_usuario)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE servicos.pacote_servico
    ADD CONSTRAINT fk1il4dcmgqg7ynmr01uindlwgx FOREIGN KEY (id_categoria)
    REFERENCES communs.categoria (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE servicos.prestadora_servico
    ADD CONSTRAINT fksj2d5rr37jxw51tg210o2gsv1 FOREIGN KEY (id_dado_empresa)
    REFERENCES communs.dado_empresa (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE servicos.servico_empresa
    ADD CONSTRAINT fkcqrg5nvnqyhsv9qtc50w4rb0r FOREIGN KEY (id_usuario)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE servicos.servico_empresa
    ADD CONSTRAINT fkd1xxb8ftrckk530gad1k866hq FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE servicos.servico_empresa
    ADD CONSTRAINT fkmsmek9vlivy4w6nj3ixdr48rb FOREIGN KEY (id_pacote_servico)
    REFERENCES servicos.pacote_servico (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE servicos.servico_empresa
    ADD CONSTRAINT fkpeht7lb55gedscrt50dkudill FOREIGN KEY (id_prestadora_servico)
    REFERENCES servicos.prestadora_servico (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


INSERT INTO communs.usuario(id,ativo, data_cadastro, email, login, nome, senha, id_empreendimento, id_funcionario)
	VALUES (1,true,'2017-12-23','root@suporte.com.br','root','Usuario root', 
			'$2a$10$HfixpsFVsIyNFzUIhTh5puP/siW/v/9H2ZUFgqlAiv8MX8JUEBEo.', null, null);

INSERT INTO communs.permissao(
	id,descricao, rol, tipo_modulo)
	VALUES 
(NEXTVAL('communs.permissao_id_seq'),'Módulo administrador','MODULO_ADMIN','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Empresa contradata','EMPRESA_CONTRATADA_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Empresa contradata','EMPRESA_CONTRATADA_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Empresa contradata','EMPRESA_CONTRATADA_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_AREA_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_AREA_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_AREA_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_FABRICANTE_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_FABRICANTE_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_FABRICANTE_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_FORNECEDOR_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_FORNECEDOR_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_FORNECEDOR_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_PRODUTO_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_PRODUTO_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_PRODUTO_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_PRODUTO_CATEGORIA_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_PRODUTO_CATEGORIA_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_PRODUTO_CATEGORIA_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_PRODUTO_TIPO_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_PRODUTO_TIPO_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CADASTROS_PRODUTO_TIPO_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CHAMADO','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CHAMADO_INFORMATICA_USUARIO','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CHAMADO_INFORMATICA_SUPORTE','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'Módulo de cadastros','MODULO_CHAMADO_INFORMATICA_SUPORTE_RELATORIO','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_CHAMADO_MANUTENCAO_USUARIO','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_CHAMADO_MANUTENCAO_SUPORTE','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_CHAMADO_MANUTENCAO_SUPORTE_RELATORIO','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_COMPRAS','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_COMPRAS_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_COMPRAS_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_COMPRAS_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_COMPRAS_COTACAO_GERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_COMPRAS_COTACAO_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_COMPRAS_COTACAO_ABERTO','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_COMPRAS_COTACAO_VENCEDORES_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo de cadastros','MODULO_COMPRAS_COTACAO_CONCORRENTES_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo estoque','MODULO_ESTOQUE','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'ENTRADA','MODULO_ESTOQUE_ENTRADA','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'ENTRADA','MODULO_ESTOQUE_CONSULTA','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'ROLE_MODULO_COMPRAS_ALTERA_LIMITE','MODULO_ESTOQUE_ALTERA_LIMITE','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'ENTRADA','MODULO_REQUISICAO_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo estoque','MODULO_ESTOQUE_TRANSFERENCIA_ENVIADAS','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo estoque','MODULO_ESTOQUE_TRANSFERENCIA_RECEBIDAS','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo estoque','MODULO_ESTOQUE_TRANSFERENCIA_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo estoque','MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_ENVIADAS','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'modulo estoque','MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_RECEBIDAS','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','MODULO_GERENCIAMENTO','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','MODULO_GERENCIAMENTO_EMPREENDIMENTO_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','MODULO_GERENCIAMENTO_EMPREENDIMENTO_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','MODULO_GERENCIAMENTO_EMPREENDIMENTO_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','MODULO_GERENCIAMENTO_EMPREENDIMENTO_INFORMACOES','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','EMPREENDIMENTO_CONFIGURACAO_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','EMPREENDIMENTO_CONFIGURACAO_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','EMPREENDIMENTO_CONFIGURACAO_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','USUARIO_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','USUARIO_EMPREENDIMENTO_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','USUARIO_EMPREENDIMENTO_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','USUARIO_EMPREENDIMENTO_PERMISSSAO','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','USUARIO_PERMISSAO_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','USUARIO_PERMISSAO_EMPREENDIMENTO_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO GERENCIAMENTO','USUARIO_PERMISSAO_EMPREENDIMENTO_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO RH','MODULO_RECURSOS_HUMANOS','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO RH','MODULO_RECURSOS_HUMANOS_CARGO_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO RH','MODULO_RECURSOS_HUMANOS_CARGO_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO RH','MODULO_RECURSOS_HUMANOS_CARGO_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO RH','MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO RH','MODULO_RECURSOS_HUMANOS_FUNCIONARIO_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO RH','MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO SERVIÇOS','MODULO_SERVICOS','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO SERVIÇOS','PACOTES_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO SERVIÇOS','PACOTES_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO SERVIÇOS','PACOTES_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO SERVIÇOS','PRESTADORA_SERVICOS_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO SERVIÇOS','PRESTADORA_SERVICOS_ALTERAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO SERVIÇOS','PRESTADORA_SERVICOS_CONSULTAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO SERVIÇOS','VINCULAR_CADASTRAR','MODULO_CHAMADO'),
(NEXTVAL('communs.permissao_id_seq'),'MODULO SERVIÇOS','VINCULAR_CONSULTAR','MODULO_CHAMADO');

 