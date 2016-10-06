package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;
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

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;

@Entity
@SequenceGenerator(name = "transferencia_id_seq",
sequenceName = "transferencia_id_seq",
initialValue = 1,
allocationSize = 1)
@Table(name = "transferencia")
public class Transferencia implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "transferencia_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_empreendimento_saida",nullable = false)
	private Empreendimento empreendimentoSaida;
	
	@ManyToOne
	@JoinColumn(name = "id_empreendimento_destino",nullable = false)
	private Empreendimento empreendimentoDestino;
	
	@OneToMany(mappedBy = "transferencia", cascade = CascadeType.ALL)
	private List<ItemTransferencia> itens;
	
	
	@Column(nullable = true)
	private String observacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empreendimento getEmpreendimentoSaida() {
		return empreendimentoSaida;
	}

	public void setEmpreendimentoSaida(Empreendimento empreendimentoSaida) {
		this.empreendimentoSaida = empreendimentoSaida;
	}

	public Empreendimento getEmpreendimentoDestino() {
		return empreendimentoDestino;
	}

	public void setEmpreendimentoDestino(Empreendimento empreendimentoDestino) {
		this.empreendimentoDestino = empreendimentoDestino;
	}

	public List<ItemTransferencia> getItens() {
		return itens;
	}

	public void setItens(List<ItemTransferencia> itens) {
		this.itens = itens;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double total()  {
		
		Double totalItem = 0.0;
		Integer quantidadeItem = 0;
		for(ItemTransferencia item : itens)
		{
			totalItem += item.getValorUnitario();
			quantidadeItem += item.getQuantidade();
		}
		return totalItem * quantidadeItem;
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
		Transferencia other = (Transferencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
