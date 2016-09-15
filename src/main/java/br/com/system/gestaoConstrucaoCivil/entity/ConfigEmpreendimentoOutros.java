package br.com.system.gestaoConstrucaoCivil.entity;
 
import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
 
@Entity
@SequenceGenerator(name = "config_outros_id_seq", sequenceName = "config_outros_id_seq", initialValue = 1, allocationSize = 1)
@Table(name = "config_empreendimento_outros")
public class ConfigEmpreendimentoOutros implements Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_outros_id_seq")
	private Long id;

     
    @Column(nullable = false,length = 250)
    private String descricao;
 
    @ManyToOne
	@JoinColumn(name="id_empreendimento",nullable = true)
    Empreendimento empreendimento;
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getDescricao() {
        return descricao;
    }
 
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
 
    
    public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConfigEmpreendimentoOutros other = (ConfigEmpreendimentoOutros) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
     
     
     
}