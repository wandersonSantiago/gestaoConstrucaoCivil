package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusCotacao;
import br.com.system.gestaoConstrucaoCivil.util.data.LocalDateDeserializer;
import br.com.system.gestaoConstrucaoCivil.util.data.LocalDateSerializer;

@Entity
@SequenceGenerator(name = "cotacao_id_seq", sequenceName = "cotacao_id_seq", initialValue = 1, allocationSize = 1)
@Table(name = "cotacao")
public class Cotacao implements Serializable{

	@JsonView(View.Summary.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_id_seq")
	private Long id;

	@JsonView(View.Summary.class)
	@Column(nullable = false)
	private String tema;
	
	@JsonView(View.Summary.class)
	//@JsonIgnore
	@OneToMany(mappedBy = "cotacao",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ItemCotacao> itens;
	
	@JsonView(View.Summary.class)
	@ManyToOne
	@JoinColumn(name = "id_empreendimento",nullable = false)
	private Empreendimento empreendimento;
	
	@JsonView(View.Summary.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataLimite;
	
	@JsonView(View.Summary.class)
	@Enumerated(EnumType.STRING)
	private StatusCotacao statusCotacao;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
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

	public LocalDate getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(LocalDate dataLimite) {
		this.dataLimite = dataLimite;
	}
   

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
    
	public StatusCotacao getStatusCotacao() {
		return statusCotacao;
	}

	public void setStatusCotacao(StatusCotacao statusCotacao) {
		this.statusCotacao = statusCotacao;
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
