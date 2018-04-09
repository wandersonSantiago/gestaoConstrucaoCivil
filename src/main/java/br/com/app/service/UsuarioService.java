package br.com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Usuario;
import br.com.app.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscarUsuarioPorNome(String nomeUsuario) {
		return usuarioRepository.findByNome(nomeUsuario);
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(Usuario usuario) {
		String hash = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(hash);
		usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = false)
	public void alterarSenha(Long idUsuario, String senha) {
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		String newHash = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(newHash);
		usuarioRepository.save(usuario);
	}

	public Optional<Usuario> buscarUsuarioPorId(Long id) {
		return usuarioRepository.findById(id);
	}

	public List<Usuario> buscarTodos() {

		return usuarioRepository.findAll();
	}

	public Usuario buscarPorLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

	public boolean existeLoginCadastrado(String login) {
		return usuarioRepository.existeLogin(login);
	}
}
