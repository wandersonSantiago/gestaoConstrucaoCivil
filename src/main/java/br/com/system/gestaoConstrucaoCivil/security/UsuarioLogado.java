package br.com.system.gestaoConstrucaoCivil.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import br.com.system.gestaoConstrucaoCivil.entity.Usuario;

public class UsuarioLogado extends User {

	private Usuario usuario;
	
	public UsuarioLogado(Usuario usuario)
	{
		super(usuario.getNome(),usuario.getSenha(),AuthorityUtils.createAuthorityList());
		this.usuario = usuario;
	}
	
	
	public Long getId()
	{
		return usuario.getId();
	}
	
}
