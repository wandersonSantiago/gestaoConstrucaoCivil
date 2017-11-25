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
	id_usuario_cadastro int8 NULL,
	CONSTRAINT produto_pkey PRIMARY KEY (id),
	CONSTRAINT uk_a32tv48ww0lvru07p7se4c15o UNIQUE (codigo),
	CONSTRAINT uk_ch9vhi0a5s30o477fb50ook3 UNIQUE (codigo_barra),
	CONSTRAINT fk6udkd7k6wb1ijuurahxkb9s71 FOREIGN KEY (id_tipo) REFERENCES almoxarifado.tipo_produto(id),
	CONSTRAINT fkbb0k43mtsufg8bfhq0gyaxhhm FOREIGN KEY (id_categoria) REFERENCES communs.categoria(id),
	CONSTRAINT fkmnq277j56jv03yc6qw8prvump FOREIGN KEY (id_fabricante) REFERENCES almoxarifado.fabricante(id),
	CONSTRAINT fkmpktkp12i8q71rxi6ikr4shgj FOREIGN KEY (id_usuario_cadastro) REFERENCES communs.usuario(id)
)