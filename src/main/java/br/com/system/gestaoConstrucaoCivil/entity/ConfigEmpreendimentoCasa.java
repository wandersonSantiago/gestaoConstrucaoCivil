package br.com.system.gestaoConstrucaoCivil.entity;
 
import java.io.Serializable;
 
import javax.persistence.Entity;
import javax.persistence.Table;
 
@Entity
@Table(name = "config_empreendimento_casa")
public class ConfigEmpreendimentoCasa extends ConfigEmpreendimento implements Serializable {
 
    public Integer teste;
 
    public Integer getTeste() {
        return teste;
    }
 
    public void setTeste(Integer teste) {
        this.teste = teste;
    }
}