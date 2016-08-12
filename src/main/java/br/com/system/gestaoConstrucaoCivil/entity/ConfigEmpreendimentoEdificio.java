package br.com.system.gestaoConstrucaoCivil.entity;
 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
 
@Entity
@Table(name = "config_empreendimento_edificio")
public class ConfigEmpreendimentoEdificio extends ConfigEmpreendimento implements Serializable{
 
	@Column(nullable = false,length = 10)
    private Integer quantidadeAndarPorTorre;
   
	@Column(nullable = false,length = 10)
    private Integer quantidadeTorre;
     
	@Column(nullable = false,length = 10)
    private Integer quantidadeApartamentoPorAndar;
 
    public Integer getQuantidadeAndarPorTorre() {
        return quantidadeAndarPorTorre;
    }
 
    public void setQuantidadeAndarPorTorre(Integer quantidadeAndarPorTorre) {
        this.quantidadeAndarPorTorre = quantidadeAndarPorTorre;
    }
 
    
    public Integer getQuantidadeTorre() {
		return quantidadeTorre;
	}

	public void setQuantidadeTorre(Integer quantidadeTorre) {
		this.quantidadeTorre = quantidadeTorre;
	}

	public Integer getQuantidadeApartamentoPorAndar() {
        return quantidadeApartamentoPorAndar;
    }
 
    public void setQuantidadeApartamentoPorAndar(Integer quantidadeApartamentoPorAndar) {
        this.quantidadeApartamentoPorAndar = quantidadeApartamentoPorAndar;
    }
}