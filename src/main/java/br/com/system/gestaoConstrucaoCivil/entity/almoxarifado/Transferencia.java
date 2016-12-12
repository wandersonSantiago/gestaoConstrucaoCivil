package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.enuns.Situacao;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusTransferencia;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoNotaEnum;
import br.com.system.gestaoConstrucaoCivil.util.GeraCodigo;

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
	@JoinColumn(name = "id_empreendimento_destino",nullable = false)
	private Empreendimento empreendimentoDestino;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_nota_fiscal", nullable = true)
	private NotaFiscal notaFiscal;
	
	@Enumerated(EnumType.STRING)
	private StatusTransferencia statusTransferencia;

	@OneToMany(mappedBy = "transferencia", cascade = CascadeType.ALL)
	private List<TransferenciaItem> itens;
	
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    public Empreendimento getEmpreendimentoDestino() {
		return empreendimentoDestino;
	}

	public void setEmpreendimentoDestino(Empreendimento empreendimentoDestino) {
		this.empreendimentoDestino = empreendimentoDestino;
	}

	public List<TransferenciaItem> getItens() {
		return itens;
	}

	public void setItens(List<TransferenciaItem> itens) {
		this.itens = itens;
	}
   public StatusTransferencia getStatusTransferencia() {
		return statusTransferencia;
	}

	public void setStatusTransferencia(StatusTransferencia statusTransferencia) {
		this.statusTransferencia = statusTransferencia;
	}

	public Double total()  {
		
		Double totalItem = 0.0;
		Integer quantidadeItem = 0;
		for(TransferenciaItem item : itens)
		{
			totalItem += item.getValorUnitario();
			quantidadeItem += item.getQuantidade();
		}
		return totalItem * quantidadeItem;
	}
	public void novaTransferencia()
	{
		statusTransferencia  = StatusTransferencia.PENDENTE;
		getNotaFiscal().setSituacao(Situacao.OK);
		getNotaFiscal().setTipoNota(TipoNotaEnum.TRANSFERENCIA_ESTOQUE_EMPREENDIMENTO);
		getNotaFiscal().setDataNota(new Date());
		getNotaFiscal().setNumero(new GeraCodigo(100000,9999999).gerarNumeroTransferencia().longValue());
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
