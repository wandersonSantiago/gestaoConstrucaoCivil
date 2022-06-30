CREATE SCHEMA almoxarifado;
CREATE SCHEMA chamado;
CREATE SCHEMA communs;
CREATE SCHEMA servicos;

CREATE TABLE almoxarifado.cotacao (
    id bigint NOT NULL,
    data_criacao date,
    data_fechamento date,
    data_limite date,
    status_cotacao character varying(255),
    tema character varying(255) NOT NULL,
    id_empreendimento bigint NOT NULL
);

CREATE TABLE almoxarifado.cotacao_empresa (
    id bigint NOT NULL,
    ganhou boolean,
    id_cotacao bigint NOT NULL,
    id_fornecedor bigint NOT NULL
);

CREATE SEQUENCE almoxarifado.cotacao_empresa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.cotacao_empresa_item (
    id bigint NOT NULL,
    observacao character varying(255),
    status character varying(255),
    valor_unitario double precision NOT NULL,
    id_cotacao_empresa bigint NOT NULL,
    id_item bigint NOT NULL
);

CREATE SEQUENCE almoxarifado.cotacao_empresa_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE almoxarifado.cotacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.cotacao_item (
    id bigint NOT NULL,
    descricao character varying(255) NOT NULL,
    quantidade integer NOT NULL,
    id_cotacao bigint
);

CREATE TABLE almoxarifado.estoque_empreendimento (
    id bigint NOT NULL,
    localizacao character varying(255),
    quantidade integer NOT NULL,
    quantidade_maxima integer NOT NULL,
    quantidade_minima integer NOT NULL,
    id_empreendimento bigint,
    id_produto bigint
);

CREATE SEQUENCE almoxarifado.estoque_empreendimento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.fabricante (
    id bigint NOT NULL,
    contato character varying(50),
    observacao character varying(50),
    id_dado_empresa bigint NOT NULL
);

CREATE SEQUENCE almoxarifado.fabricante_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.fornecedor (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    contato character varying(50),
    observacao character varying(50),
    id_dado_empresa bigint NOT NULL
);

CREATE SEQUENCE almoxarifado.fornecedor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE almoxarifado.item_cotacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE almoxarifado.item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.nota_fiscal (
    id bigint NOT NULL,
    data_nota date,
    data_vencimento date,
    numero bigint NOT NULL,
    observacao character varying(255),
    situacao character varying(255),
    tipo_nota character varying(255),
    valor_total double precision NOT NULL,
    id_empreendimento bigint
);

CREATE SEQUENCE almoxarifado.nota_fiscal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.nota_fiscal_item (
    id bigint NOT NULL,
    quantidade integer NOT NULL,
    valor_unitario double precision NOT NULL,
    id_produto bigint NOT NULL,
    valor_total double precision NOT NULL,
    id_nota_fiscal bigint
);

CREATE TABLE almoxarifado.nota_fiscal_produto (
    id bigint NOT NULL,
    id_fornecedor bigint NOT NULL,
    id_nota_fiscal bigint
);

CREATE SEQUENCE almoxarifado.nota_fiscal_produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.nota_fiscal_servico (
    id bigint NOT NULL,
    id_nota_fiscal bigint
);

CREATE SEQUENCE almoxarifado.nota_fiscal_servico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.pedido_compra (
    id bigint NOT NULL,
    data_cadastro timestamp without time zone,
    numero_pedido integer,
    previsao date,
    empreendimento_id bigint,
    fornecedor_id bigint,
    usuario_cadastro bigint
);

CREATE SEQUENCE almoxarifado.pedido_compra_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.pedido_item (
    id bigint NOT NULL,
    quantidade integer NOT NULL,
    valor_unitario double precision NOT NULL,
    id_produto bigint NOT NULL,
    id_pedido_compra bigint
);

CREATE TABLE almoxarifado.produto (
    id bigint NOT NULL,
    ativo boolean,
    codigo integer,
    codigo_barra character varying(50) NOT NULL,
    descricao character varying(50) NOT NULL,
    unidade_medida character varying(255),
    id_categoria bigint,
    id_fabricante bigint,
    id_tipo bigint
);

CREATE SEQUENCE almoxarifado.produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.requisicao (
    id bigint NOT NULL,
    data_criacao date,
    data_saida date,
    id_usuario bigint,
    numero_requisicao integer,
    observacao character varying(255),
    status_requisicao character varying(255),
    id_empreendimento bigint,
    id_estrutura bigint
);

CREATE SEQUENCE almoxarifado.requisicao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.requisicao_item (
    id bigint NOT NULL,
    quantidade integer NOT NULL,
    valor_unitario double precision NOT NULL,
    id_produto bigint NOT NULL,
    id_requisicao bigint
);

CREATE TABLE almoxarifado.tipo_produto (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    descricao character varying(30) NOT NULL
);

CREATE SEQUENCE almoxarifado.tipo_produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.transferencia (
    id bigint NOT NULL,
    status_transferencia character varying(255),
    id_empreendimento_destino bigint NOT NULL,
    id_nota_fiscal bigint
);

CREATE SEQUENCE almoxarifado.transferencia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE almoxarifado.transferencia_item (
    id bigint NOT NULL,
    quantidade integer NOT NULL,
    valor_unitario double precision NOT NULL,
    id_produto bigint NOT NULL,
    id_transferencia bigint
);

CREATE SEQUENCE chamado.chamado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE chamado.chamado_manutencao (
    id bigint NOT NULL,
    data_abertura timestamp without time zone,
    data_fechamento timestamp without time zone,
    lido boolean,
    prioridade character varying(255),
    silenciar boolean,
    status character varying(255),
    titulo character varying(255),
    id_empreendimento bigint,
    id_usuario_atendente bigint,
    id_usuario_solicitante bigint,
    descricao_servico character varying(255)
);

CREATE TABLE chamado.chamado_ti (
    id bigint NOT NULL,
    data_abertura timestamp without time zone,
    data_fechamento timestamp without time zone,
    lido boolean,
    prioridade character varying(255),
    silenciar boolean,
    status character varying(255),
    titulo character varying(255),
    id_empreendimento bigint,
    id_usuario_atendente bigint,
    id_usuario_solicitante bigint,
    descricao_servico character varying(255),
    tipo_equipamento character varying(255)
);

CREATE TABLE chamado.mensagem (
    id bigint NOT NULL,
    arquivo smallint,
    data timestamp without time zone,
    texto character varying(255),
    id_chamado bigint,
    id_usuario bigint
);

CREATE SEQUENCE chamado.mensagem_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.aluguel_equipamento (
    id bigint NOT NULL,
    nome_equipamento character varying(255),
    preco_locacao double precision,
    valor_aquisicao double precision
);

CREATE SEQUENCE communs.aluguel_equipamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.area_produto (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    descricao character varying(255)
);

CREATE SEQUENCE communs.area_produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE communs.arquivo_produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.arquivo_upload (
    id bigint NOT NULL,
    file bytea,
    proprietario_id bigint
);

CREATE TABLE communs.cargo (
    id bigint NOT NULL,
    descricao character varying(255)
);

CREATE SEQUENCE communs.cargo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.categoria (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    descricao character varying(255) NOT NULL,
    categoria_id bigint
);

CREATE SEQUENCE communs.categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.codigo_gerado (
    id bigint NOT NULL,
    codigo character varying(255),
    data_geracao date
);

CREATE SEQUENCE communs.codigo_gerado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.dado_empresa (
    id bigint NOT NULL,
    cnpj character varying(20) NOT NULL,
    email character varying(40),
    inscricao_estadual character varying(20) NOT NULL,
    nome_fantasia character varying(50) NOT NULL,
    razao_social character varying(50) NOT NULL,
    site character varying(40),
    status integer,
    telefone character varying(15) NOT NULL,
    uf_ie character varying(255),
    id_endereco bigint NOT NULL
);

CREATE SEQUENCE communs.dado_empresa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE communs.empreendimento (
    id bigint NOT NULL,
    data_abertura date,
    data_fechamento date,
    descricao character varying(50) NOT NULL,
    porcentagem double precision NOT NULL,
    status character varying(255),
    telefone character varying(255),
    valor_maximo_gastar double precision NOT NULL,
    valores_gastos double precision NOT NULL,
    id_endereco bigint NOT NULL,
    matriz_id bigint
);

CREATE SEQUENCE communs.empreendimento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.empresa_contratante (
    id bigint NOT NULL,
    id_dado_empresa bigint NOT NULL
);

CREATE SEQUENCE communs.empresa_contratante_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.endereco (
    id bigint NOT NULL,
    bairro character varying(50) NOT NULL,
    cep character varying(9) NOT NULL,
    complemento character varying(50),
    localidade character varying(50) NOT NULL,
    logradouro character varying(50) NOT NULL,
    numero integer NOT NULL,
    uf character varying(255) NOT NULL
);

CREATE SEQUENCE communs.endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.estrutura (
    id bigint NOT NULL,
    descricao character varying(255),
    id_empreendimento bigint,
    raiz_id bigint
);

CREATE SEQUENCE communs.estrutura_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.funcionario (
    ativo boolean NOT NULL,
    carteira_trabalho integer NOT NULL,
    crea integer,
    data_admissao date,
    id bigint NOT NULL,
    id_cargo bigint,
    id_empreendimento bigint
);

CREATE TABLE communs.morador (
    id bigint NOT NULL,
    cpf character varying(255),
    data_nascimento date,
    email character varying(255),
    nome_completo character varying(255),
    telefone_celular character varying(255),
    telefone_fixo character varying(255),
    id_empreendimento bigint,
    id_usuario bigint
);

CREATE SEQUENCE communs.morador_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.permissao (
    id bigint NOT NULL,
    descricao character varying(255),
    rol character varying(255),
    tipo_modulo character varying(255)
);

CREATE SEQUENCE communs.permissao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.pessoa (
    id bigint NOT NULL,
    cpf character varying(20) NOT NULL,
    data_nascimento date,
    estado_civil character varying(255),
    idade integer NOT NULL,
    nome_completo character varying(50) NOT NULL,
    rg character varying(20) NOT NULL,
    sexo character varying(255) NOT NULL,
    telefone_celular character varying(15) NOT NULL,
    telefone_fixo character varying(15) NOT NULL,
    id_endereco bigint NOT NULL
);

CREATE SEQUENCE communs.pessoa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.usuario (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    caminho_foto character varying(255),
    data_cadastro date,
    email character varying(40) NOT NULL,
    login character varying(50) NOT NULL,
    nome character varying(50) NOT NULL,
    senha character varying(256) NOT NULL,
    status character varying(255),
    id_empreendimento bigint
);

CREATE SEQUENCE communs.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE communs.usuario_permissoes (
    id_usuario bigint NOT NULL,
    id_permissoes bigint NOT NULL
);

CREATE TABLE almoxarifado.requisicao_itens (
    requisicao_id bigint NOT NULL,
    itens_id bigint NOT NULL
);

CREATE TABLE almoxarifado.tb_fornecedor_produto (
    fornecedor_id bigint NOT NULL,
    produto_id bigint NOT NULL
);

CREATE TABLE servicos.ocorrencia_servico (
    id bigint NOT NULL,
    arquivo smallint,
    data timestamp without time zone,
    ocorrencia character varying(255),
    id_servico_empresa bigint,
    id_usuario bigint
);

CREATE SEQUENCE servicos.ocorrencia_servico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE servicos.pacote_servico (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    codigo integer NOT NULL,
    criterio_servico character varying(250) NOT NULL,
    descricao character varying(50) NOT NULL,
    servicos_atribuidos character varying(250) NOT NULL,
    valor double precision NOT NULL,
    id_categoria bigint
);

CREATE SEQUENCE servicos.pacote_servico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE servicos.prestadora_servico (
    id bigint NOT NULL,
    id_dado_empresa bigint NOT NULL
);

CREATE SEQUENCE servicos.prestadora_servico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE servicos.servico_empresa (
    id bigint NOT NULL,
    data_cadastro timestamp without time zone,
    data_encerramento_servico timestamp without time zone,
    data_fechamento timestamp without time zone,
    data_pagamento timestamp without time zone,
    observacao character varying(255),
    porcentagem double precision,
    valor_adicional double precision,
    valor_desconto double precision,
    valor_pacote_servico double precision,
    valor_total_pago double precision,
    id_empreendimento bigint NOT NULL,
    id_pacote_servico bigint NOT NULL,
    id_prestadora_servico bigint NOT NULL,
    id_usuario bigint
);

CREATE SEQUENCE servicos.servico_empresa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ONLY almoxarifado.cotacao_empresa_item
    ADD CONSTRAINT cotacao_empresa_item_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.cotacao_empresa
    ADD CONSTRAINT cotacao_empresa_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.cotacao_item
    ADD CONSTRAINT cotacao_item_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.cotacao
    ADD CONSTRAINT cotacao_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.estoque_empreendimento
    ADD CONSTRAINT estoque_empreendimento_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.fabricante
    ADD CONSTRAINT fabricante_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.fornecedor
    ADD CONSTRAINT fornecedor_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.nota_fiscal_item
    ADD CONSTRAINT nota_fiscal_item_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.nota_fiscal
    ADD CONSTRAINT nota_fiscal_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.nota_fiscal_produto
    ADD CONSTRAINT nota_fiscal_produto_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.nota_fiscal_servico
    ADD CONSTRAINT nota_fiscal_servico_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.pedido_compra
    ADD CONSTRAINT pedido_compra_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.pedido_item
    ADD CONSTRAINT pedido_item_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.requisicao_item
    ADD CONSTRAINT requisicao_item_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.requisicao
    ADD CONSTRAINT requisicao_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.tipo_produto
    ADD CONSTRAINT tipo_produto_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.transferencia_item
    ADD CONSTRAINT transferencia_item_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.transferencia
    ADD CONSTRAINT transferencia_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.produto
    ADD CONSTRAINT uk_a32tv48ww0lvru07p7se4c15o UNIQUE (codigo);

ALTER TABLE ONLY almoxarifado.produto
    ADD CONSTRAINT uk_ch9vhi0a5s30o477fb50ook3 UNIQUE (codigo_barra);

ALTER TABLE ONLY chamado.chamado_manutencao
    ADD CONSTRAINT chamado_manutencao_pkey PRIMARY KEY (id);

ALTER TABLE ONLY chamado.chamado_ti
    ADD CONSTRAINT chamado_ti_pkey PRIMARY KEY (id);

ALTER TABLE ONLY chamado.mensagem
    ADD CONSTRAINT mensagem_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.aluguel_equipamento
    ADD CONSTRAINT aluguel_equipamento_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.area_produto
    ADD CONSTRAINT area_produto_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.arquivo_upload
    ADD CONSTRAINT arquivo_upload_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.codigo_gerado
    ADD CONSTRAINT codigo_gerado_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.dado_empresa
    ADD CONSTRAINT dado_empresa_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.empreendimento
    ADD CONSTRAINT empreendimento_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.empresa_contratante
    ADD CONSTRAINT empresa_contratante_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.estrutura
    ADD CONSTRAINT estrutura_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.morador
    ADD CONSTRAINT morador_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);

ALTER TABLE ONLY communs.dado_empresa
    ADD CONSTRAINT uk_3q2sq2h6wbhqm3su4ocl9jdek UNIQUE (inscricao_estadual);

ALTER TABLE ONLY communs.pessoa
    ADD CONSTRAINT uk_97gwi9nbsq9hgi7xtclbp7p7d UNIQUE (rg);

ALTER TABLE ONLY communs.dado_empresa
    ADD CONSTRAINT uk_gr6ybd264h7cak0efphth6895 UNIQUE (cnpj);

ALTER TABLE ONLY communs.usuario_permissoes
    ADD CONSTRAINT uk_ly0d3vxdtxffyrke1emaitqe5 UNIQUE (id_permissoes);

ALTER TABLE ONLY communs.pessoa
    ADD CONSTRAINT uk_nlwiu48rutiltbnjle59krljo UNIQUE (cpf);

ALTER TABLE ONLY communs.usuario_permissoes
    ADD CONSTRAINT usuario_permissoes_pkey PRIMARY KEY (id_usuario, id_permissoes);

ALTER TABLE ONLY communs.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.requisicao_itens
    ADD CONSTRAINT uk_jxl42dwy8b33ldy9cjv5aauy7 UNIQUE (itens_id);

ALTER TABLE ONLY servicos.ocorrencia_servico
    ADD CONSTRAINT ocorrencia_servico_pkey PRIMARY KEY (id);

ALTER TABLE ONLY servicos.pacote_servico
    ADD CONSTRAINT pacote_servico_pkey PRIMARY KEY (id);

ALTER TABLE ONLY servicos.prestadora_servico
    ADD CONSTRAINT prestadora_servico_pkey PRIMARY KEY (id);

ALTER TABLE ONLY servicos.servico_empresa
    ADD CONSTRAINT servico_empresa_pkey PRIMARY KEY (id);

ALTER TABLE ONLY almoxarifado.pedido_compra
    ADD CONSTRAINT fk2ot6wgakmwkla0yo8dj0gnti FOREIGN KEY (usuario_cadastro) REFERENCES communs.usuario(id);

ALTER TABLE ONLY almoxarifado.cotacao
    ADD CONSTRAINT fk3uf322eo2e48otekwukhmkv5d FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY almoxarifado.transferencia_item
    ADD CONSTRAINT fk4rrrdja6x52qugntd2i1911i9 FOREIGN KEY (id_transferencia) REFERENCES almoxarifado.transferencia(id);

ALTER TABLE ONLY almoxarifado.cotacao_empresa
    ADD CONSTRAINT fk5ik5waqtlkyg3segkksv53k58 FOREIGN KEY (id_fornecedor) REFERENCES almoxarifado.fornecedor(id);

ALTER TABLE ONLY almoxarifado.cotacao_item
    ADD CONSTRAINT fk6dvm4jhys9bnsu2ht26blw11x FOREIGN KEY (id_cotacao) REFERENCES almoxarifado.cotacao(id);

ALTER TABLE ONLY almoxarifado.produto
    ADD CONSTRAINT fk6udkd7k6wb1ijuurahxkb9s71 FOREIGN KEY (id_tipo) REFERENCES almoxarifado.tipo_produto(id);

ALTER TABLE ONLY almoxarifado.nota_fiscal_produto
    ADD CONSTRAINT fk7e6khna2xrye1yi2r88qrb1ft FOREIGN KEY (id_nota_fiscal) REFERENCES almoxarifado.nota_fiscal(id);

ALTER TABLE ONLY almoxarifado.requisicao
    ADD CONSTRAINT fk7r6f2lgge6bb70h9jcuyjdiwe FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY almoxarifado.pedido_item
    ADD CONSTRAINT fk_5guwi7yl0kr0jhrqqp8ciiy8l FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id);

ALTER TABLE ONLY almoxarifado.requisicao_item
    ADD CONSTRAINT fk_igkuc6o07qn7lvh2q0jh93pjo FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id);

ALTER TABLE ONLY almoxarifado.transferencia_item
    ADD CONSTRAINT fk_o8og11ycjyq85w7wya8t8uuro FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id);

ALTER TABLE ONLY almoxarifado.nota_fiscal_item
    ADD CONSTRAINT fk_pkhw9mey9p4khg82dx5sggks8 FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id);

ALTER TABLE ONLY almoxarifado.nota_fiscal
    ADD CONSTRAINT fkaop1c471vp2mvbmh5hnjy8qdf FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY almoxarifado.produto
    ADD CONSTRAINT fkbb0k43mtsufg8bfhq0gyaxhhm FOREIGN KEY (id_categoria) REFERENCES communs.categoria(id);

ALTER TABLE ONLY almoxarifado.transferencia
    ADD CONSTRAINT fkbjfjb49kw03pkms53g9y6ftbk FOREIGN KEY (id_empreendimento_destino) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY almoxarifado.requisicao
    ADD CONSTRAINT fke447mex0sjcfku37jkxe4ll24 FOREIGN KEY (id_estrutura) REFERENCES communs.estrutura(id);

ALTER TABLE ONLY almoxarifado.cotacao_empresa_item
    ADD CONSTRAINT fkfnjvdjh2ot3sl6t9n8kh49o41 FOREIGN KEY (id_item) REFERENCES almoxarifado.cotacao_item(id);

ALTER TABLE ONLY almoxarifado.cotacao_empresa_item
    ADD CONSTRAINT fki0kc0ffk28aqsflohwr6pds3g FOREIGN KEY (id_cotacao_empresa) REFERENCES almoxarifado.cotacao_empresa(id);

ALTER TABLE ONLY almoxarifado.fabricante
    ADD CONSTRAINT fkiphtgfonvn8ua5hxi9qb2wie8 FOREIGN KEY (id_dado_empresa) REFERENCES communs.dado_empresa(id);

ALTER TABLE ONLY almoxarifado.pedido_compra
    ADD CONSTRAINT fkjkayk8gmdo1hmmnjt42si5lat FOREIGN KEY (fornecedor_id) REFERENCES almoxarifado.fornecedor(id);

ALTER TABLE ONLY almoxarifado.fornecedor
    ADD CONSTRAINT fkkx1g1djx4amdhnwpcmo8w65xc FOREIGN KEY (id_dado_empresa) REFERENCES communs.dado_empresa(id);

ALTER TABLE ONLY almoxarifado.nota_fiscal_servico
    ADD CONSTRAINT fkl2wvfnr1180ylxntkmbkm8yxm FOREIGN KEY (id_nota_fiscal) REFERENCES almoxarifado.nota_fiscal(id);

ALTER TABLE ONLY almoxarifado.cotacao_empresa
    ADD CONSTRAINT fkl6eecabk5w415c6vmm7gcepsd FOREIGN KEY (id_cotacao) REFERENCES almoxarifado.cotacao(id);

ALTER TABLE ONLY almoxarifado.pedido_compra
    ADD CONSTRAINT fkl9l3e7h7xdar0er8547kgj02c FOREIGN KEY (empreendimento_id) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY almoxarifado.nota_fiscal_item
    ADD CONSTRAINT fklkb3jiffubruk1t3noslwn8sp FOREIGN KEY (id_nota_fiscal) REFERENCES almoxarifado.nota_fiscal_produto(id);

ALTER TABLE ONLY almoxarifado.nota_fiscal_produto
    ADD CONSTRAINT fkm6w4dn7y218lglboggxpitgw7 FOREIGN KEY (id_fornecedor) REFERENCES almoxarifado.fornecedor(id);

ALTER TABLE ONLY almoxarifado.produto
    ADD CONSTRAINT fkmnq277j56jv03yc6qw8prvump FOREIGN KEY (id_fabricante) REFERENCES almoxarifado.fabricante(id);

ALTER TABLE ONLY almoxarifado.pedido_item
    ADD CONSTRAINT fkpxbymligfrhcod4fwex0bf9pq FOREIGN KEY (id_pedido_compra) REFERENCES almoxarifado.pedido_compra(id);

ALTER TABLE ONLY almoxarifado.requisicao_item
    ADD CONSTRAINT fkqxn9el871jm5c6posgu5x38ql FOREIGN KEY (id_requisicao) REFERENCES almoxarifado.requisicao(id);

ALTER TABLE ONLY almoxarifado.estoque_empreendimento
    ADD CONSTRAINT fkr2qr2j67m9w1g2oavyw07tg0e FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id);

ALTER TABLE ONLY almoxarifado.estoque_empreendimento
    ADD CONSTRAINT fkswjr3wknleay6troekt0melr8 FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY almoxarifado.transferencia
    ADD CONSTRAINT fksxl1n2yy9y4qilx9llcwtq3l1 FOREIGN KEY (id_nota_fiscal) REFERENCES almoxarifado.nota_fiscal(id);

ALTER TABLE ONLY chamado.chamado_ti
    ADD CONSTRAINT fk_4710yn7v1q4qlgtuhob4eh1a0 FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY chamado.chamado_manutencao
    ADD CONSTRAINT fk_amd9cv3wpiqsa8d0ya68mggxt FOREIGN KEY (id_usuario_solicitante) REFERENCES communs.usuario(id);

ALTER TABLE ONLY chamado.chamado_ti
    ADD CONSTRAINT fk_dr4d1ayiir1ibdm4l6ndyy0n8 FOREIGN KEY (id_usuario_atendente) REFERENCES communs.usuario(id);

ALTER TABLE ONLY chamado.chamado_ti
    ADD CONSTRAINT fk_e66owwifteo1jx0lxl8e230d9 FOREIGN KEY (id_usuario_solicitante) REFERENCES communs.usuario(id);

ALTER TABLE ONLY chamado.chamado_manutencao
    ADD CONSTRAINT fk_hrnhw0vphbt9pndx33oufwkmj FOREIGN KEY (id_usuario_atendente) REFERENCES communs.usuario(id);

ALTER TABLE ONLY chamado.chamado_manutencao
    ADD CONSTRAINT fk_iq5ovrup6fjjc32lilgfc3mir FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY chamado.mensagem
    ADD CONSTRAINT fkqlofg9o3fjv2wk4r49o0abm5e FOREIGN KEY (id_usuario) REFERENCES communs.usuario(id);

ALTER TABLE ONLY communs.empreendimento
    ADD CONSTRAINT fk3bnkecah0rqbbi5p9f1h1ejf9 FOREIGN KEY (id_endereco) REFERENCES communs.endereco(id);

ALTER TABLE ONLY communs.morador
    ADD CONSTRAINT fk56q7xfnyjj342l49waw0nm9st FOREIGN KEY (id_usuario) REFERENCES communs.usuario(id);

ALTER TABLE ONLY communs.funcionario
    ADD CONSTRAINT fk92othhhe25gno2qtus5woc6ys FOREIGN KEY (id_cargo) REFERENCES communs.cargo(id);

ALTER TABLE ONLY communs.empreendimento
    ADD CONSTRAINT fk9bk4mw4xxmiw2dpmmqc5lt07m FOREIGN KEY (matriz_id) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY communs.usuario_permissoes
    ADD CONSTRAINT fkbgsbgiea8o1yvo1lh7aylh9ln FOREIGN KEY (id_permissoes) REFERENCES communs.permissao(id);

ALTER TABLE ONLY communs.categoria
    ADD CONSTRAINT fkjiixv3teefdkilvoysutbedif FOREIGN KEY (categoria_id) REFERENCES communs.categoria(id);

ALTER TABLE ONLY communs.funcionario
    ADD CONSTRAINT fkjs6iadl1wroqdibh61lp5dhnb FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY communs.pessoa
    ADD CONSTRAINT fko2v72927b43j7ug0s8b97ymly FOREIGN KEY (id_endereco) REFERENCES communs.endereco(id);

ALTER TABLE ONLY communs.usuario_permissoes
    ADD CONSTRAINT fkow1uauurnis7iu0i3wr6s76ih FOREIGN KEY (id_usuario) REFERENCES communs.usuario(id);

ALTER TABLE ONLY communs.funcionario
    ADD CONSTRAINT fkpjvvctvfjm69dvye3aea5k56f FOREIGN KEY (id) REFERENCES communs.pessoa(id);

ALTER TABLE ONLY communs.dado_empresa
    ADD CONSTRAINT fkqcsf4g30c72uprjlkaepvh3yt FOREIGN KEY (id_endereco) REFERENCES communs.endereco(id);

ALTER TABLE ONLY communs.empresa_contratante
    ADD CONSTRAINT fkqqhnmknub93nv5jbspbmu8e2e FOREIGN KEY (id_dado_empresa) REFERENCES communs.dado_empresa(id);

ALTER TABLE ONLY communs.morador
    ADD CONSTRAINT fkrhbqa13y7y0tv4qkm40tbnsyc FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY communs.estrutura
    ADD CONSTRAINT fkrkye45rrfdxvetm0xjvwflfpf FOREIGN KEY (raiz_id) REFERENCES communs.estrutura(id);

ALTER TABLE ONLY communs.usuario
    ADD CONSTRAINT fkrta44oqpseerxmyluygyy2xdk FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY almoxarifado.requisicao_itens
    ADD CONSTRAINT fk18lrixpbjh4ftueqblqi4y3n0 FOREIGN KEY (itens_id) REFERENCES almoxarifado.requisicao_item(id);

ALTER TABLE ONLY almoxarifado.requisicao_itens
    ADD CONSTRAINT fk9jrut5weyey4wjdlgvh2spfj FOREIGN KEY (requisicao_id) REFERENCES almoxarifado.requisicao(id);

ALTER TABLE ONLY almoxarifado.tb_fornecedor_produto
    ADD CONSTRAINT fkd23b02emrptqd1iy29hkrcg9 FOREIGN KEY (fornecedor_id) REFERENCES almoxarifado.fornecedor(id);

ALTER TABLE ONLY almoxarifado.tb_fornecedor_produto
    ADD CONSTRAINT fknei3majp5iqoo45v1f1btt2p FOREIGN KEY (produto_id) REFERENCES almoxarifado.produto(id);

ALTER TABLE ONLY servicos.pacote_servico
    ADD CONSTRAINT fk1il4dcmgqg7ynmr01uindlwgx FOREIGN KEY (id_categoria) REFERENCES communs.categoria(id);

ALTER TABLE ONLY servicos.ocorrencia_servico
    ADD CONSTRAINT fkaiap4d7xj3lis6ypy84enu8lk FOREIGN KEY (id_servico_empresa) REFERENCES servicos.servico_empresa(id);

ALTER TABLE ONLY servicos.servico_empresa
    ADD CONSTRAINT fkcqrg5nvnqyhsv9qtc50w4rb0r FOREIGN KEY (id_usuario) REFERENCES communs.usuario(id);

ALTER TABLE ONLY servicos.servico_empresa
    ADD CONSTRAINT fkd1xxb8ftrckk530gad1k866hq FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id);

ALTER TABLE ONLY servicos.ocorrencia_servico
    ADD CONSTRAINT fkfqkdofn6un2nd2h3wsqw03286 FOREIGN KEY (id_usuario) REFERENCES communs.usuario(id);

ALTER TABLE ONLY servicos.servico_empresa
    ADD CONSTRAINT fkmsmek9vlivy4w6nj3ixdr48rb FOREIGN KEY (id_pacote_servico) REFERENCES servicos.pacote_servico(id);
ALTER TABLE ONLY servicos.servico_empresa
    ADD CONSTRAINT fkpeht7lb55gedscrt50dkudill FOREIGN KEY (id_prestadora_servico) REFERENCES servicos.prestadora_servico(id);

ALTER TABLE ONLY servicos.prestadora_servico
    ADD CONSTRAINT fksj2d5rr37jxw51tg210o2gsv1 FOREIGN KEY (id_dado_empresa) REFERENCES communs.dado_empresa(id);
   
   
   
INSERT INTO communs.usuario(id,ativo, data_cadastro, email, login, nome, senha, id_empreendimento)
	VALUES (1,true,'2017-12-23','root@suporte.com.br','root','Usuario root', 
			'$2a$10$HfixpsFVsIyNFzUIhTh5puP/siW/v/9H2ZUFgqlAiv8MX8JUEBEo.', null);   
   
