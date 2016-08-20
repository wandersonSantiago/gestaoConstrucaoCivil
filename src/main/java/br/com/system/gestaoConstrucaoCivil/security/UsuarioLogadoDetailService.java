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
public class UsuarioLogadoDetailService /*implements UserDetailsService*/{

	/*@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
		
		Usuario usuario ;
		try
		{
		   usuario = usuarioService.buscarUsuarioPorNome(nomeUsuario);
		   
		}catch (Exception e) {
			throw new  UsernameNotFoundException("Usuário " + nomeUsuario +"  não encontrado");
		}
		return new UsuarioLogado(usuario);
	}*/

}
