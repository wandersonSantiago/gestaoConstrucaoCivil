package br.com.app.entity.servicos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.entity.Usuario;

@Entity
@SequenceGenerator(name = "ocorrencia_servico_id_seq", sequenceName = "ocorrencia_servico_id_seq",allocationSize = 1, schema = "servicos")
@Table(name = "ocorrencia_servico", schema = "servicos")
public class OcorrenciaServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ocorrencia_servico_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_servico_empresa")
	private ServicoEmpresa servicoEmpresa;

	@Column(name = "ocorrencia")
	private String ocorrencia;

	@Column(name = "data")
	private Date data;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column(name = "arquivo")
	private Byte arquivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServicoEmpresa getServicoEmpresa() {
		return servicoEmpresa;
	}

	public void setServicoEmpresa(ServicoEmpresa servicoEmpresa) {
		this.servicoEmpresa = servicoEmpresa;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Byte getArquivo() {
		return arquivo;
	}

	public void setArquivo(Byte arquivo) {
		this.arquivo = arquivo;
	}

}
