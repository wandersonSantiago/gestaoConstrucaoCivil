package br.com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Empreendimento;
import br.com.app.entity.Usuario;
import br.com.app.enuns.StatusUsuarioEnum;
import br.com.app.exceptions.NotFoundException;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EmpreendimentoService empreendimentoService;

	@Transactional(readOnly = false)
	public Usuario insert(Usuario usuario) {
		Usuario user = SessionUsuario.getInstance().getUsuario();
		if (usuario.getId() == null) {
			if (usuario.getEmpreendimento() == null) {
				usuario.setEmpreendimento(user.getEmpreendimento());
			}
			String hash = new BCryptPasswordEncoder().encode(usuario.getSenha());
			usuario.setSenha(hash);
			usuario.setAtivo(true);
			usuario.setStatus(StatusUsuarioEnum.OFFLINE);
			return usuarioRepository.save(usuario);
		}
		return null;
	}

	@Transactional(readOnly = false)
	public Usuario update(Usuario usuario) {
		Usuario userSession = SessionUsuario.getInstance().getUsuario();
		if (usuario.getId() != null) {
			if (usuario.getEmpreendimento() == null) {
				usuario.setEmpreendimento(userSession.getEmpreendimento());
			}
			Usuario user = usuarioRepository.findById(usuario.getId())
					.orElseThrow(() -> new NotFoundException("Usuário não foi encontrado!"));
			String hash = user.getSenha();
			usuario.setSenha(hash);
			return usuarioRepository.save(usuario);
		}
		return null;
	}

	@Transactional(readOnly = false)
	public void updatePassword(Long idUsuario, String senhaValidacao, String novaSenha) {
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new NotFoundException("Usuário não foi encontrado!"));
		String newHash = new BCryptPasswordEncoder().encode(novaSenha);
		usuario.setSenha(newHash);
		usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = false)
	public void updateEmpreendimento(Long idEmpreendimento) {
		Usuario user = SessionUsuario.getInstance().getUsuario();
		Empreendimento empreendimento = empreendimentoService.findById(idEmpreendimento);
		user.setEmpreendimento(empreendimento);
		usuarioRepository.save(user);
	}

	@Transactional(readOnly = false)
	public void savePathFoto(Usuario user) {
		usuarioRepository.save(user);
	}

	public Usuario findById(Long id) {
		
		Boolean hasEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento()  != null;
		
		if(hasEmpreendimento) {
			Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
			return usuarioRepository.findByIdAndEmpreendimentoId(id, idEmpreendimento);
		}else {
			
			Usuario usuarioSession = SessionUsuario.getInstance().getUsuario();
			if(usuarioSession.isRoot()) {
				return usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não foi encontrado!"));
			}
		}
		throw new NotFoundException("Usuário não foi encontrado! ");
		
		
		
	
	}
	public Usuario findByIdAndEmpreendimento(Long usuarioId,Long empreendimentoId) {
		return usuarioRepository.findByIdAndEmpreendimentoId(usuarioId, empreendimentoId);
	}
	public Usuario findByNome(String nomeUsuario) {
		return usuarioRepository.findByNome(nomeUsuario);
	}

	public List<Usuario> findAll() {

		return usuarioRepository.findAll();
	}

	public Usuario findByLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

	public boolean existeLoginCadastrado(String login) {
		return usuarioRepository.existeLogin(login);
	}

	public Page<Usuario> findAll(Pageable page) {
		Usuario user = SessionUsuario.getInstance().getUsuario();
		if (user.isRoot()) {
			return usuarioRepository.findAll(page);
		}
		if (user.getEmpreendimento().isMatriz()) {
			return usuarioRepository.findByEmpreendimentoMatriz_id(user.getEmpreendimento().getId(), page);
		}
		return usuarioRepository.findByEmpreendimento_id(user.getEmpreendimento().getId(), page);
	}

	public Page<Usuario> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Usuario user = SessionUsuario.getInstance().getUsuario();
		if (user.isRoot()) {
			return usuarioRepository.findByNomeContainsIgnoreCase(descricao, page);
		}
		if (user.getEmpreendimento().isMatriz()) {
			return usuarioRepository.findByNomeContainsIgnoreCaseAndEmpreendimento_matriz_id(descricao,
					user.getEmpreendimento().getId(), page);
		}
		return usuarioRepository.findByNomeContainsIgnoreCaseAndEmpreendimento_id(descricao,
				user.getEmpreendimento().getId(), page);
	}

}
