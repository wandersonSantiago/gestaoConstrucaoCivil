package br.com.app.infraestructure.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.app.commons.domain.model.Usuario;
import br.com.app.commons.domain.service.UsuarioService;

@Component
public class LoginHandler implements ApplicationListener<AuthenticationSuccessEvent>{

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private HttpSession session;
	
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {

		String login = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
		
		Usuario usuario = usuarioService.findByLogin(login);
		if(usuario != null)
		{
			session.setAttribute("usuario", usuario);
		}
	}

}
