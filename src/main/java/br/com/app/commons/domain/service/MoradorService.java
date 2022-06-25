package br.com.app.commons.domain.service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.commons.domain.model.Morador;
import br.com.app.commons.domain.model.Usuario;
import br.com.app.commons.domain.repository.MoradorRepository;
import br.com.app.infraestructure.security.SessionUsuario;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class MoradorService {

	@Autowired
	private MoradorRepository clienteMoradorRepository;
	
	public Page<Morador> buscarTodos(PageRequest page) {
		
		return  clienteMoradorRepository.findAll(page);
	}	
	public Optional<Morador> buscarPorId(Long id) {
		return clienteMoradorRepository.findById(id);
	}
	public Morador buscarPorCpf(String cpf) {
		return clienteMoradorRepository.findByCpf(cpf);
	}
	public Collection<Morador> lista() {		
		return clienteMoradorRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Morador cliente)
	{ 
		salvaAtributosCliente(cliente);		
		cliente.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		clienteMoradorRepository.save(cliente);
	}
	
	public Morador salvaAtributosCliente(Morador cliente){
			Boolean ativo = true;
			Usuario user =  new Usuario();
			user.setAtivo(ativo);
			user.setDataCadastro(new Date());
			user.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
			user.setNome(cliente.getNomeCompleto());
			user.setLogin(cliente.getCpf());
			user.setEmail(cliente.getEmail());
			String hash = new BCryptPasswordEncoder().encode(cliente.getCpf());
			user.setSenha(hash);
			cliente.setUsuario(user);			
		return cliente;
	}
}
