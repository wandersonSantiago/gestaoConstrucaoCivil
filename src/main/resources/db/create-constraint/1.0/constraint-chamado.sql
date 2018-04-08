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