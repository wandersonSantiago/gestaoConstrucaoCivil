package br.com.app.generate;

import br.com.app.entity.Usuario;
import br.com.app.enuns.StatusUsuarioEnum;

public class GenerateUsuario {

	
	public Usuario gerar() {
		Usuario user = new Usuario();
		user.setAtivo(true);
		user.setEmail("josesilva@suport.com.br");
		user.setNome("Jose Silva");
		user.setSenha("123");
		user.setLogin("josesilva");
		user.setStatus(StatusUsuarioEnum.ONLINE);
		return user;
	}
}
