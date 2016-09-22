package br.com.system.gestaoConstrucaoCivil.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.system.gestaoConstrucaoCivil.GestaoConstrucaoCivilApplication;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.repository.UsuarioRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GestaoConstrucaoCivilApplication.class)
@WebIntegrationTest
public class Criptografia {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void criptografarSenhaUsuario()
	{
		Usuario usuario = usuarioRepository.findByLogin("eduardo");
		String hash = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(hash);
		usuarioRepository.save(usuario);
	}
}
