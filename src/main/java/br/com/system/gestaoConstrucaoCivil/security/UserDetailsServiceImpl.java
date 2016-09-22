package br.com.system.gestaoConstrucaoCivil.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.service.UsuarioService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		
		System.out.println("Metodo loadUserByUsername");
		Usuario usuario = usuario = usuarioService.buscarPorLogin(username);
		if(usuario != null)
		{
			UserDetailsImpl user = new UserDetailsImpl();
			//user.setUsername(usuario.getId().toString());
			user.setUsername(usuario.getLogin());
			user.setPassword(usuario.getSenha());
		 
			
			return user;
		}
		throw new UsernameNotFoundException("Usuario não encontrado");
	}

}
