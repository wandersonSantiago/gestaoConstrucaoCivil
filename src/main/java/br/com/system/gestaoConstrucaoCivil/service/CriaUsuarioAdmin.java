package br.com.system.gestaoConstrucaoCivil.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.enuns.PerfilUsuarioEnum;


@Component
public class CriaUsuarioAdmin implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private UsuarioService usuarioService;
	private final Logger log = LoggerFactory.getLogger(CriaUsuarioAdmin.class);
	
	private void criarUsuario()
	{
		if(usuarioService.existeLoginCadastrado("root") == false)
		{
			log.warn("Usuário root não encontrado.");
			log.info("Criando usuário root.");
			usuarioService.salvarOuEditar(criarObjetoUsuario());
			log.info("Usuário root criado.");
		}else
		{
			log.info("Usuário root [OK]");
		}
			
	}
    private Usuario criarObjetoUsuario()
    {
    	Usuario usuario = new Usuario();
		usuario.setAtivo(true);
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
