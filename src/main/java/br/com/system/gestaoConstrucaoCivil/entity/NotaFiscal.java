package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.enuns.TipoNotaEnum;

@Entity
@SequenceGenerator(name = "nota_fiscal_id_seq", sequenceName = "nota_fiscal_id_seq", initialValue = 1, allocationSize = 50)
@Table(name = "nota_fiscal")
public class NotaFiscal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_fiscal_id_seq")
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoNotaEnum tipoNota;

	@Column(nullable = false)
	private Integer numero;

	@Column(nullable = false)
	private Integer chaveAcesso;

	@Temporal(TemporalType.DATE)
	private Date dataNota;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	@Column(nullable = false)
	private Integer serie;

	private String observacao;
	@Column(nullable = false)
	private Double valorTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(Integer chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
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

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public TipoNotaEnum getTipoNota() {
		return tipoNota;
	}

	public void setTipoNota(TipoNotaEnum tipoNota) {
		this.tipoNota = tipoNota;
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
