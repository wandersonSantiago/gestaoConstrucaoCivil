package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;

@Entity
@SequenceGenerator(name = "cotacao_id_seq", sequenceName = "cotacao_id_seq", initialValue = 1, allocationSize = 1)
@Table(name = "cotacao")
public class Cotacao implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_id_seq")
	private Long id;

	@Column(nullable = false)
	private String tema;
	
	
	@OneToMany(mappedBy = "cotacao",cascade = CascadeType.ALL)
	private List<ItemCotacao> itens;
	
	@ManyToOne
	@JoinColumn(name = "id_empreendimento",nullable = false)
	private Empreendimento empreendimento;
	
	@Temporal(TemporalType.DATE)
	private Date dataLimite;
	
	private LocalDate dataCriacao;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<ItemCotacao> getItens() {
		return itens;
	}

	public void setItens(List<ItemCotacao> itens) {
		this.itens = itens;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}
   

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
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
		Cotacao other = (Cotacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
