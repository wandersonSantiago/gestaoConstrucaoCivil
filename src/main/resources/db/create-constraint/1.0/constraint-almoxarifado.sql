ALTER TABLE almoxarifado.cotacao
    ADD CONSTRAINT fk3uf322eo2e48otekwukhmkv5d FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.cotacao
    ADD CONSTRAINT fkqw2qn9gar5vfuomoa82wpryxt FOREIGN KEY (id_usuario_cadastro)
    REFERENCES communs.usuario (id) MATCH SIMPLE
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


ALTER TABLE almoxarifado.informacao_requisicao
    ADD CONSTRAINT fkt7sjoojs82j8k1wa17tpfhshv FOREIGN KEY (id_usuario_cadastro)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.informacao_requisicao
    ADD CONSTRAINT fkt68d7ovqioxsyoqgkphignglq FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE almoxarifado.nota_fiscal
    ADD CONSTRAINT fkaop1c471vp2mvbmh5hnjy8qdf FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.nota_fiscal
    ADD CONSTRAINT fkcf6e8wurt93t0in22lb4yfxip FOREIGN KEY (id_usuario_cadastro)
    REFERENCES communs.usuario (id) MATCH SIMPLE
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

ALTER TABLE almoxarifado.produto
    ADD CONSTRAINT fkmpktkp12i8q71rxi6ikr4shgj FOREIGN KEY (id_usuario_cadastro)
    REFERENCES communs.usuario (id) MATCH SIMPLE
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

ALTER TABLE almoxarifado.requisicao_casa
    ADD CONSTRAINT fkakd5om8pumpgdvpmspik8runc FOREIGN KEY (id_informacao_requisicao)
    REFERENCES almoxarifado.informacao_requisicao (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.requisicao_casa_item
    ADD CONSTRAINT fk_5o70snciavqb8vgh31s0i7n7s FOREIGN KEY (id_produto)
    REFERENCES almoxarifado.produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.requisicao_casa_item
    ADD CONSTRAINT fket524shhx51yo3nldteg3ku7x FOREIGN KEY (id_requisicao_casa)
    REFERENCES almoxarifado.requisicao_casa (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.requisicao_casa_item
    ADD CONSTRAINT fkjd6xtrok9rrwicmhl1tlpirko FOREIGN KEY (id_area_produto)
    REFERENCES communs.area_produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.requisicao_edificio
    ADD CONSTRAINT fk615bgmifvj42uvesq9nuh45c6 FOREIGN KEY (id_informacao_requisicao)
    REFERENCES almoxarifado.informacao_requisicao (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.requisicao_edificio_item
    ADD CONSTRAINT fk_hocfiglysps7g3r1i24n4pals FOREIGN KEY (id_produto)
    REFERENCES almoxarifado.produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.requisicao_edificio_item
    ADD CONSTRAINT fklx33tvrnpfsrex2euppomx2cj FOREIGN KEY (id_area_produto)
    REFERENCES communs.area_produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.requisicao_edificio_item
    ADD CONSTRAINT fkn5ekny0broufc9y188cgbm0me FOREIGN KEY (id_requisicao_edificio)
    REFERENCES almoxarifado.requisicao_edificio (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.requisicao_outros
    ADD CONSTRAINT fkaxv3nm4o74mgq60jmr08y4ep5 FOREIGN KEY (id_informacao_requisicao)
    REFERENCES almoxarifado.informacao_requisicao (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.requisicao_outros_item
    ADD CONSTRAINT fk_akwpju4kalx8x7ayj0ju7knqe FOREIGN KEY (id_produto)
    REFERENCES almoxarifado.produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.requisicao_outros_item
    ADD CONSTRAINT fki0ksnckuv49bvmsw80kybk7k9 FOREIGN KEY (id_area_produto)
    REFERENCES communs.area_produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE almoxarifado.requisicao_outros_item
    ADD CONSTRAINT fknt7qmvy9jh37m7f0mc0cdnwf2 FOREIGN KEY (id_requisicao_outros)
    REFERENCES almoxarifado.requisicao_outros (id) MATCH SIMPLE
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


