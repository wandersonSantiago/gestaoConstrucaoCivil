package br.com.system.gestaoConstrucaoCivil.pojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.system.gestaoConstrucaoCivil.entity.Usuario;

public class SessionUsuario {

	public static SessionUsuario instance;

	private HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
			.getRequest();

	private SessionUsuario() {

	}

	public Usuario getUsuario() {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		return usuario;
	}

	public static SessionUsuario getInstance() {

		if (instance == null) {
			instance = new SessionUsuario();

		}

		return instance;
	}
}
