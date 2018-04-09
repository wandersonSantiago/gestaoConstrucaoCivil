package br.com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.PermissaoUsuario;
import br.com.app.repository.PermissaoUsuarioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PermissaoUsuarioService {

	@Autowired
	private PermissaoUsuarioRepository permissaoUsuarioRepository;

	@Transactional(readOnly = false)
	public void salvarOuEditar(PermissaoUsuario permissao) {
		permissaoUsuarioRepository.save(permissao);
	}

	public List<PermissaoUsuario> buscarTodos() {

		return permissaoUsuarioRepository.findAll();
	}

	public List<PermissaoUsuario> buscarPorIdUsuario(Long id) {
		return permissaoUsuarioRepository.findByUsuario_id(id);
	}

	@Transactional(readOnly = false)
	public void removerPermissao(Long permissao) {
		permissaoUsuarioRepository.delete(permissaoUsuarioRepository.findById(permissao).get());
	}

}
