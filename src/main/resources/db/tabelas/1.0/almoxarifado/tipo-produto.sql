--liquibase formatted sql

--changeset Eduardo Ferrari:1
--comment: creating tipo_produto table and SEQUENCE almoxarifado.tipo_produto_id_seq

CREATE SEQUENCE almoxarifado.tipo_produto_id_seq START 1;

CREATE TABLE almoxarifado.tipo_produto (
	id int8 NOT NULL DEFAULT nextval('almoxarifado.tipo_produto_id_seq'::regclass),
	ativo bool NOT NULL,
	descricao varchar(30) NOT NULL,
	CONSTRAINT tipo_produto_pkey PRIMARY KEY (id)
)