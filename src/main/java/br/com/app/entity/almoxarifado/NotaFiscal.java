package br.com.app.entity.almoxarifado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.app.entity.Empreendimento;
import br.com.app.entity.Usuario;
import br.com.app.enuns.Situacao;
import br.com.app.enuns.TipoNotaEnum;
import br.com.app.pojo.SessionUsuario;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "nota_fiscal_id_seq", sequenceName = "nota_fiscal_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "nota_fiscal", schema = "almoxarifado")
public class NotaFiscal implements Serializable{

	
	private static final long serialVersionUID = 5220537450104744028L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_fiscal_id_seq")
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoNotaEnum tipoNota;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	@Column(nullable = false)
	private Long numero;

	@ManyToOne
	@JoinColumn(name = "id_empreendimento", nullable = true)
	private Empreendimento empreendimento;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nota")
	private Date dataNota;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento")
	private Date dataVencimento;

	@Column(nullable = true)
	private String observacao;

	@Column(nullable = false)
	private Double valorTotal;

	public void cancelarNota() {
		this.situacao = Situacao.CANCELADA;
	}

	public void novaNota() {
		this.situacao = Situacao.OK;
		Usuario usuarioSessao = SessionUsuario.getInstance().getUsuario();
		this.empreendimento = usuarioSessao.getEmpreendimento();

	}

}
