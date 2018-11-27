package br.com.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.enuns.StatusEnum;
import br.com.app.enuns.UfEnum;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "dado_empresa_id_seq", sequenceName = "dado_empresa_id_seq", allocationSize = 1, schema = "communs")
@Table(name = "dado_empresa", schema = "communs")
public class DadoEmpresa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dado_empresa_id_seq")
	private Long id;

	private static final long serialVersionUID = 1L;
	@Column(nullable = false, length = 50)
	private String razaoSocial;
	@Column(nullable = false, length = 50)
	private String nomeFantasia;
	@Column(nullable = false, length = 20, unique = true)
	private String cnpj;
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	protected UfEnum ufIe;
	@Column(nullable = true, length = 20, unique = true)
	private String inscricaoEstadual;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_endereco", nullable = false)
	private Endereco endereco;
	@Column(nullable = false, length = 15)
	private String telefone;
	@Column(length = 40)
	private String email;
	@Column(length = 40)
	private String site;
	
	private Integer status;
	
	
	public void setStatus(StatusEnum status) {
		this.status = status.getCodigo();
	}
	public StatusEnum getStatus() {
		return StatusEnum.toEnum(status);
	}
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial.toUpperCase();
	}
	
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia.toUpperCase();
	}
}
