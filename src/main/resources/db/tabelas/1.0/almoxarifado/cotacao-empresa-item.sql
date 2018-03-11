--liquibase formatted sql

--changeset Eduardo Ferrari:1
--comment: creating cotacao_empresa_item table and SEQUENCE almoxarifado.cotacao_empresa_item_id_seq

CREATE SEQUENCE almoxarifado.cotacao_empresa_item_id_seq START 1;

CREATE TABLE almoxarifado.cotacao_empresa_item (
	id int8 NOT NULL DEFAULT nextval('almoxarifado.cotacao_empresa_item_id_seq'::regclass),
	observacao varchar(255) NULL,
	status varchar(255) NULL,
	valor_unitario float8 NOT NULL,
	id_cotacao_empresa int8 NOT NULL,
	id_item int8 NOT NULL,
	CONSTRAINT cotacao_empresa_item_pkey PRIMARY KEY (id)
)
--rollback drop table cotacao_empresa_item;