package br.com.app.service;


import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.app.entity.Permissao;
import br.com.app.entity.PermissaoUsuario;
import br.com.app.entity.Usuario;
import br.com.app.repository.PermissaoRepository;


@Component
public class CriaUsuarioAdmin {

	@Autowired
	private UsuarioService usuarioService;
	private final Logger log = LoggerFactory.getLogger(CriaUsuarioAdmin.class);
	@Autowired
	private PermissaoRepository permissaoRepository;
	@Autowired
	private PermissaoUsuarioService permissaoUsuarioService;
	
	
	@EventListener(ContextRefreshedEvent.class)
    void contextRefreshedEvent() {
       
		//criarUsuario();
    }
	private void criarUsuario()
	{
		
		if(usuarioService.existeLoginCadastrado("root") == false)
		{
			log.warn("Usuário root não encontrado.");
			log.info("Criando usuário root.");
			usuarioService.salvarOuEditar(criarObjetoUsuario());
			log.info("Usuário root criado.");
			colocarTodasPermissaosNoRoot();
		}else
		{
			log.info("Usuário root [OK]");
		}
			
	}
	
	private void colocarTodasPermissaosNoRoot()
	{
		List<Permissao> permissoes = permissaoRepository.findAll();
		Usuario usuarioRoot = usuarioService.buscarPorLogin("root");
		
		for(Permissao permissao : permissoes)
		{
			PermissaoUsuario permissaoUsuario = new PermissaoUsuario();
			permissaoUsuario.setPermissao(permissao);
			permissaoUsuario.setUsuario(usuarioRoot);
		    permissaoUsuarioService.salvarOuEditar(permissaoUsuario);
		    
		}
	}

    private Usuario criarObjetoUsuario()
    {
    	
    	Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setLogin("root");
		usuario.setSenha(pedirSenha());
		usuario.setEmail("root@suporte.com.br");
		usuario.setNome("Usuario root");
	    return usuario;
    }
    private String pedirSenha()
    {
    	 
    	Scanner lerSenha = new Scanner(System.in);
    	 System.out.println("Digite uma senha para o usuário root:");
    	  String senha = lerSenha.nextLine();
    	  lerSenha.close();
    	  return senha;
    	
    	
    }
}
