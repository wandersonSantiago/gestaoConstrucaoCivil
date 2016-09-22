package br.com.system.gestaoConstrucaoCivil.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.repository.UsuarioRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class UsuarioService {


	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Usuario buscarUsuarioPorNome(String nomeUsuario)
	{
		 return usuarioRepository.findByNome(nomeUsuario);
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Usuario usuario)
	{
		String hash = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(hash);
		usuario.setDataCadastro(LocalDate.now());
		usuarioRepository.save(usuario);
	}
	
	public Usuario buscarUsuarioPorId(Long id){
		return usuarioRepository.findOne(id);
	}
	
	public List<Usuario> buscarTodos(){
			
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorLogin(String login)
	{
		return usuarioRepository.findByLogin(login);
	}
	
	public boolean existeLoginCadastrado(String login)
	{
		return usuarioRepository.existeLogin(login);
	}
}
