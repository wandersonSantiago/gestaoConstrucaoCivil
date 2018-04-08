 
ALTER TABLE communs.cliente_morador
    ADD CONSTRAINT fk50ck7ntnxk6vikumnokvpbapt FOREIGN KEY (id_usuario)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

 ALTER TABLE communs.cliente_morador
    ADD CONSTRAINT fkccdqopx6bey5um5abshj9hae5 FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;   

 ALTER TABLE communs.cliente_morador_casa
    ADD CONSTRAINT fkkubqdyda517otbxpner468iha FOREIGN KEY (id)
    REFERENCES communs.cliente_morador (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
   
  ALTER TABLE communs.cliente_morador_edificil
    ADD CONSTRAINT fkj68b5cp5bq4448xrn80oc4p63 FOREIGN KEY (id)
    REFERENCES communs.cliente_morador (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

   ALTER TABLE communs.cliente_morador_outros
    ADD CONSTRAINT fkrp4jl7iqgevpy204xsjm8sgbs FOREIGN KEY (id)
    REFERENCES communs.cliente_morador (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE communs.config_empreendimento
    ADD CONSTRAINT fk42d3wrr6atitet5hrtqs07org FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE communs.config_empreendimento_casa
    ADD CONSTRAINT fk2hqb3gxibukkw9bjqof1d12d2 FOREIGN KEY (id)
    REFERENCES communs.config_empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE communs.config_empreendimento_edificio
    ADD CONSTRAINT fkjywi6xuchiovb6amlgkcm63eb FOREIGN KEY (id)
    REFERENCES communs.config_empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE communs.config_empreendimento_outros
    ADD CONSTRAINT fk4kokib8th74e8kid2l4p7ugp3 FOREIGN KEY (id_empreendimento)
    REFERENCES communs.empreendimento (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE communs.dado_empresa
    ADD CONSTRAINT fkqcsf4g30c72uprjlkaepvh3yt FOREIGN KEY (id_endereco)
    REFERENCES communs.endereco (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE communs.empreendimento
    ADD CONSTRAINT fkboocxk7l4ksh0dworthd9yii0 FOREIGN KEY (id_engenheiro_responsavel_funcionario)
    REFERENCES communs.funcionario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE communs.empreendimento
    ADD CONSTRAINT fkepgj0up27svkjn18usy5rhbad FOREIGN KEY (id_endereco_empreendimento)
    REFERENCES communs.endereco (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE communs.empreendimento
    ADD CONSTRAINT fkhu4qce6ei6188u7dauligvx8h FOREIGN KEY (id_engenheiro_responsavel_terceiro)
    REFERENCES servicos.prestadora_servico (id) MATCH SIMPLE
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

    ALTER TABLE communs.permissao_usuario
    ADD CONSTRAINT fk3b2s9hbeysqtbkwrwbus7cuku FOREIGN KEY (usuario_id)
    REFERENCES communs.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE communs.permissao_usuario
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