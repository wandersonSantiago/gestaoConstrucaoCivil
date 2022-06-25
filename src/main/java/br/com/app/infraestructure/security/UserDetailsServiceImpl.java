package br.com.app.infraestructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.commons.domain.model.Usuario;
import br.com.app.commons.domain.service.UsuarioService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioService.findByLogin(username);
		if (usuario != null) {
			UserDetailsImpl user = new UserDetailsImpl();

			user.setUsername(usuario.getLogin());
			user.setPassword(usuario.getSenha());
			
			usuario.getPermissoes().forEach(p ->{
				user.addAuthority("ROLE_"+ p.getRol());
			});				
				
			
			return user;
		}
		throw new UsernameNotFoundException("Usuario não encontrado");
	}

}
