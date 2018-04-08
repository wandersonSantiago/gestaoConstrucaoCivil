CREATE TABLE almoxarifado.cotacao_empresa (
	id int8 NOT NULL,
	ganhou bool NULL,
	id_cotacao int8 NOT NULL,
	id_fornecedor int8 NOT NULL,
	CONSTRAINT cotacao_empresa_pkey PRIMARY KEY (id),
	CONSTRAINT fk5ik5waqtlkyg3segkksv53k58 FOREIGN KEY (id_fornecedor) REFERENCES almoxarifado.fornecedor(id),
	CONSTRAINT fkl6eecabk5w415c6vmm7gcepsd FOREIGN KEY (id_cotacao) REFERENCES almoxarifado.cotacao(id)
)