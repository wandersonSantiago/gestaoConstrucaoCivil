ALTER TABLE almoxarifado.cotacao ADD CONSTRAINT fk3uf322eo2e48otekwukhmkv5d FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id);
ALTER TABLE almoxarifado.cotacao ADD CONSTRAINT fkqw2qn9gar5vfuomoa82wpryxt FOREIGN KEY (id_usuario_cadastro) REFERENCES communs.usuario(id);
