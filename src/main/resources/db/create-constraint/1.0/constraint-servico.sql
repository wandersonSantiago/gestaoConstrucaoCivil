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

 ALTER TABLE servicos.servico_casa
    ADD CONSTRAINT fkn0si5f3t2ky38o6oa8ikldq36 FOREIGN KEY (id)
    REFERENCES servicos.servico_empresa (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

  ALTER TABLE servicos.servico_edificio
    ADD CONSTRAINT fk38srf829txxm1odv6fbl3jigd FOREIGN KEY (id)
    REFERENCES servicos.servico_empresa (id) MATCH SIMPLE
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

   ALTER TABLE servicos.servico_outros
    ADD CONSTRAINT fkpi7uofqcs9nl5p396fjjiv3cq FOREIGN KEY (id)
    REFERENCES servicos.servico_empresa (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;