CREATE TABLE almoxarifado.requisicao_edificio_item (
	id int8 NOT NULL,
	quantidade int4 NOT NULL,
	valor_unitario float8 NOT NULL,
	id_produto int8 NOT NULL,
	andar int4 NULL,
	apartamento int4 NULL,
	torre int4 NULL,
	id_area_produto int8 NULL,
	id_requisicao_edificio int8 NULL,
	CONSTRAINT requisicao_edificio_item_pkey PRIMARY KEY (id),
	CONSTRAINT fk_hocfiglysps7g3r1i24n4pals FOREIGN KEY (id_produto) REFERENCES almoxarifado.produto(id),
	CONSTRAINT fklx33tvrnpfsrex2euppomx2cj FOREIGN KEY (id_area_produto) REFERENCES communs.area_produto(id),
	CONSTRAINT fkn5ekny0broufc9y188cgbm0me FOREIGN KEY (id_requisicao_edificio) REFERENCES almoxarifado.requisicao_edificio(id)
)