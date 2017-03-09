package br.com.system.gestaoConstrucaoCivil.entity.servicos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.system.gestaoConstrucaoCivil.entity.Usuario;

@Entity
@Table(name="ocorrencia_servico")
public class OcorrenciaServico extends AbstractPersistable<Long> {
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_servico_empresa")
	private ServicoEmpresa servicoEmpresa;
	
	
	@Column(name="ocorrencia")
	private String ocorrencia;
	
	
	@Column(name="data")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@Column(name="arquivo")
	private Byte arquivo;

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
