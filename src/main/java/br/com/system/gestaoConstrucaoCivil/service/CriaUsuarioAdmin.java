package br.com.system.gestaoConstrucaoCivil.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.enuns.PerfilUsuarioEnum;
import br.com.system.gestaoConstrucaoCivil.repository.UsuarioRepository;

@Component
public class CriaUsuarioAdmin implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	UsuarioService usuarioService;
	
	
	private void criarUsuario()
	{
		if(usuarioService.existeLoginCadastrado("root") == false)
		{
			System.out.println("Usuário root não encontrado.");
			System.out.println("Criando usuário root.");
			usuarioService.salvarOuEditar(criarObjetoUsuario());
		    System.out.println("Usuário root criado.");
		}else
		{
			System.out.println("Usuário root [OK]");
		}
			
	}
    private Usuario criarObjetoUsuario()
    {
    	Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setDataCadastro(LocalDate.now());
		usuario.setLogin("root");
		usuario.setEmail("root@suporte.com.br");
	    usuario.setSenha("root951951");
	    usuario.setNome("Usuario root");
	    usuario.setPerfilsUsuario(Arrays.asList(PerfilUsuarioEnum.ADMIN));
	    return usuario;
    }
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		criarUsuario();
		
	}
}
