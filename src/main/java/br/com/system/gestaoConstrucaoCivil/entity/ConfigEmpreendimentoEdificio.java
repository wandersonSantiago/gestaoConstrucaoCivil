package br.com.system.gestaoConstrucaoCivil.entity;
 
import java.io.Serializable;
 
import javax.persistence.Entity;
import javax.persistence.Table;
 
@Entity
@Table(name = "config_empreendimento_edificio")
public class ConfigEmpreendimentoEdificio extends ConfigEmpreendimento implements Serializable{
 
    private Integer QuantidadeAndarPorTorre;
     
    private Integer QuantidadeApartamentoPorAndar;
 
    public Integer getQuantidadeAndarPorTorre() {
        return QuantidadeAndarPorTorre;
    }
 
    public void setQuantidadeAndarPorTorre(Integer quantidadeAndarPorTorre) {
        QuantidadeAndarPorTorre = quantidadeAndarPorTorre;
    }
 
    public Integer getQuantidadeApartamentoPorAndar() {
        return QuantidadeApartamentoPorAndar;
    }
 
    public void setQuantidadeApartamentoPorAndar(Integer quantidadeApartamentoPorAndar) {
        QuantidadeApartamentoPorAndar = quantidadeApartamentoPorAndar;
    }
}