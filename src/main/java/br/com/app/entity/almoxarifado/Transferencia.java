package br.com.app.entity.almoxarifado;

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

import br.com.app.entity.Empreendimento;
import br.com.app.entity.almoxarifado.interfaces.EntradaOuBaixa;
import br.com.app.enuns.StatusTransferencia;
import br.com.app.enuns.TipoNotaEnum;
import br.com.app.util.gerador.codigo.GeraNumeroNota;

@Entity
@SequenceGenerator(name = "transferencia_id_seq", sequenceName = "transferencia_id_seq",schema = "almoxarifado")
@Table(name = "transferencia", schema = "almoxarifado")
public class Transferencia implements Serializable, EntradaOuBaixa {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transferencia_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_empreendimento_destino", nullable = false)
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

		for (TransferenciaItem item : this.itens) {
			item.setTransferencia(this);
		}
	}

	public StatusTransferencia getStatusTransferencia() {
		return statusTransferencia;
	}

	public void novaTransferencia() {

		statusTransferencia = StatusTransferencia.PENDENTE;
		getNotaFiscal().setTipoNota(TipoNotaEnum.TRANSFERENCIA_ESTOQUE_EMPREENDIMENTO);
		getNotaFiscal().setNumero(new GeraNumeroNota().gerarNumeroNota().longValue());
		getNotaFiscal().novaNota();
		getNotaFiscal().setDataNota(new Date());

	}

	public void aceitarTransferencia() {
		statusTransferencia = StatusTransferencia.EFETUADO;
	}

	public void rejeitarTransferencia() {
		statusTransferencia = StatusTransferencia.RECUSADO;
		getNotaFiscal().cancelarNota();
	}

	@Override
	public Empreendimento empreendimentoSaida() {
		// TODO Auto-generated method stub
		return notaFiscal.getEmpreendimento();
	}

	@Override
	public Empreendimento empreendimentoEntrada() {
		// TODO Auto-generated method stub
		return empreendimentoDestino;
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
