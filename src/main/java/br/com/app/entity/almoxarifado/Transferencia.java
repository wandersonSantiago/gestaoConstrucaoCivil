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
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "transferencia_id_seq", sequenceName = "transferencia_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "transferencia", schema = "almoxarifado")
public class Transferencia implements Serializable, EntradaOuBaixa<TransferenciaItem> {

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

	
	public double getValorTotal() {
		double valor = 0.0;
		for(TransferenciaItem x : itens) {
			valor = valor + x.getValorTotal();
		}
		return valor;
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

	

	public void rejeitarTransferencia() {
		statusTransferencia = StatusTransferencia.RECUSADO;
		getNotaFiscal().cancelarNota();
	}

	@Override
	public Empreendimento empreendimentoSaida() {
		return notaFiscal.getEmpreendimento();
	}

	@Override
	public Empreendimento empreendimentoEntrada() {
		return empreendimentoDestino;
	}

}
