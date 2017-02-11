package br.com.system.gestaoConstrucaoCivil.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.system.gestaoConstrucaoCivil.entity.Permissao;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoModulo;
import br.com.system.gestaoConstrucaoCivil.repository.PermissaoRepository;


@Component
public class CriaUsuarioAdmin implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private UsuarioService usuarioService;
	private final Logger log = LoggerFactory.getLogger(CriaUsuarioAdmin.class);
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	private void criarUsuario()
	{
		
		//permissaoRepository.save(criarPermissao());
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
	/*private List<Permissao> criarPermissao()
	{
		Permissao p1 = new Permissao();
		p1.setDescricao("administrador do sistema");
		p1.setRol("ADMIN");
		p1.setTipoModulo(TipoModulo.ADMIN);
		
		Permissao p2 = new Permissao();
		p2.setDescricao("administrador da empresa");
		p2.setRol("ADMINISTRADOR_EMPRESA");
		p2.setTipoModulo(TipoModulo.ADMIN);
		
		Permissao p3 = new Permissao();
		p3.setDescricao("cadastro estoque");
		p3.setRol("ESTOQUE_CADASTRO");
		p3.setTipoModulo(TipoModulo.CADASTROS);
		
		Permissao p4 = new Permissao();
		p4.setDescricao("consulta estoque");
		p4.setRol("ESTOQUE_CONSULTA");
		p4.setTipoModulo(TipoModulo.ESTOQUE);
		
		Permissao p5 = new Permissao();
		p5.setDescricao("cadastro cotação");
		p5.setRol("COTACAO_CADASTRO");
		p5.setTipoModulo(TipoModulo.CADASTROS);
		
		Permissao p6 = new Permissao();
		p6.setDescricao("consulta cotação");
		p6.setRol("COTACAO_CONSULTA");
		p6.setTipoModulo(TipoModulo.COMPRAS);
		
		Permissao p7 = new Permissao();
		p7.setDescricao("cadastro de compras");
		p7.setRol("COMPRAS_CADASTRO");
		p7.setTipoModulo(TipoModulo.CADASTROS);
		
		Permissao p8 = new Permissao();
		p8.setDescricao("consulta de cargo");
		p8.setRol("CARGO_CONSULTA");
		p8.setTipoModulo(TipoModulo.RECURSOS_HUMANOS);
		
		Permissao p9 = new Permissao();
		p9.setDescricao("Cadastro de cargo");
		p9.setRol("CARGO_CADASTRO");
		p9.setTipoModulo(TipoModulo.CADASTROS);
		
		Permissao p10 = new Permissao();
		p10.setDescricao("gestor");
		p10.setRol("GESTOR");
		p10.setTipoModulo(TipoModulo.ADMIN);
		
		List<Permissao> permissoes = new ArrayList<Permissao>();
        permissoes.addAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10));
        return permissoes;
	}*/
    private Usuario criarObjetoUsuario()
    {
    	Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setLogin("root");
		usuario.setEmail("root@suporte.com.br");
	    usuario.setSenha("root951951");
	    usuario.setNome("Usuario root");
	//    usuario.setPerfilsUsuario(Arrays.asList(PerfilUsuarioEnum.ADMIN));
	    return usuario;
    }
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		criarUsuario();
		
	}
}
