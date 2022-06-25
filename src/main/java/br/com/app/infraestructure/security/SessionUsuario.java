package br.com.app.infraestructure.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.app.commons.domain.model.Usuario;

public class SessionUsuario {

	private static SessionUsuario instance;

	private SessionUsuario() {

	}
   public synchronized Usuario getUsuario() {
		
		 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
		HttpSession session = request.getSession();
		return (Usuario) session.getAttribute("usuario");
   }
    public static synchronized  SessionUsuario getInstance() {

		if (instance == null) {
			instance = new SessionUsuario();

		}

		return instance;
	}
}
