package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "permissao_usuario")
public class PermissaoUsuario extends AbstractPersistable<Long>{

	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Permissao permissao;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Permissao getPermissao() {
		return permissao;
	}
	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
	
	
	
}
