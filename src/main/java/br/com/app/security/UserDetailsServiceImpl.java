package br.com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.PermissaoUsuario;
import br.com.app.entity.Usuario;
import br.com.app.service.PermissaoUsuarioService;
import br.com.app.service.UsuarioService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PermissaoUsuarioService permissaoUsuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioService.buscarPorLogin(username);
		if (usuario != null) {
			UserDetailsImpl user = new UserDetailsImpl();

			user.setUsername(usuario.getLogin());
			user.setPassword(usuario.getSenha());
			
			for(PermissaoUsuario permissao :  permissaoUsuarioService.buscarPorIdUsuario(usuario.getId()))
			{
				user.addAuthority("ROLE_"+permissao.getPermissao().getRol());
				
			}
			
			return user;
		}
		throw new UsernameNotFoundException("Usuario não encontrado");
	}

}
