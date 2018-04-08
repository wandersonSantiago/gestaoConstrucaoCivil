package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

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

import com.fasterxml.jackson.annotation.JsonView;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.enuns.Situacao;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoNotaEnum;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;



@Entity
@SequenceGenerator(name = "nota_fiscal_id_seq", sequenceName = "nota_fiscal_id_seq", initialValue = 1, allocationSize = 1)
@Table(name = "nota_fiscal", schema="almoxarifado")
public class NotaFiscal implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_fiscal_id_seq")
	private Long id;

	
	@Enumerated(EnumType.STRING)
	private TipoNotaEnum tipoNota;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@JsonView(View.Summary.class)
	@Column(nullable = false)
	private Long numero;

	@JsonView(View.Summary.class)
    @ManyToOne
    @JoinColumn(name="id_empreendimento",nullable = true)
    private Empreendimento empreendimento;
    
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nota")
	private Date dataNota;
	
	@JsonView(View.Summary.class)
	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento")
	private Date dataVencimento;

	@Column(nullable = true)
	private String observacao;
	
	@JsonView(View.Summary.class)
	@Column(nullable = false)
	private Double valorTotal;

	@ManyToOne
	@JoinColumn(name ="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    public Long getNumero() {
		return numero;
	}
     
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getDataNota() {
		return dataNota;
	}

	public void setDataNota(Date dataNota) {
		this.dataNota = dataNota;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public TipoNotaEnum getTipoNota() {
		return tipoNota;
	}

	public void setTipoNota(TipoNotaEnum tipoNota) {
		this.tipoNota = tipoNota;
	}
    public Situacao getSituacao() {
		return situacao;
	}
    public void cancelarNota()
    {
    	this.situacao = Situacao.CANCELADA;
    }

	public void novaNota()
	{
		this.situacao = Situacao.OK;
		Usuario usuarioSessao = SessionUsuario.getInstance().getUsuario();
		this.empreendimento = usuarioSessao.getEmpreendimento();
		this.usuarioCadastro = usuarioSessao;
	}
	
	
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
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
		NotaFiscal other = (NotaFiscal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
