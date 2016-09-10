package br.com.system.gestaoConstrucaoCivil.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.service.UsuarioService;

@Component
public class LoginHandler implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		
		String userId = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
		Usuario usuario = usuarioService.buscarUsuarioPorId(new Integer(userId).longValue());
		if(usuario != null)
		{
			session.setAttribute("usuario", usuario);
		}
	}

}
