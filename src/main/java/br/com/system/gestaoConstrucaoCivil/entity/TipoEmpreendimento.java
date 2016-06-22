package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "tipo_empreendimento")
public class TipoEmpreendimento extends AbstractPersistable<Long> {

	
	@Column(nullable = false,length = 50)
	private String descricaoTipoEmpreendimento;
	

	public String getDescricaoTipoEmpreendimento() {
		return descricaoTipoEmpreendimento;
	}
	public void setDescricaoTipoEmpreendimento(String descricaoTipoEmpreendimento) {
		this.descricaoTipoEmpreendimento = descricaoTipoEmpreendimento;
	}

}
