package br.com.app.service;

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

import br.com.app.entity.ClienteMorador;
import br.com.app.entity.Usuario;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.ClienteMoradorRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ClienteMoradorService {

	@Autowired
	private ClienteMoradorRepository clienteMoradorRepository;
	
	public Page<ClienteMorador> buscarTodos(PageRequest page) {
		
		return  clienteMoradorRepository.findAll(page);
	}	
	public Optional<ClienteMorador> buscarPorId(Long id) {
		return clienteMoradorRepository.findById(id);
	}
	public ClienteMorador buscarPorCpf(String cpf) {
		return clienteMoradorRepository.findByCpf(cpf);
	}
	public Collection<ClienteMorador> lista() {		
		return clienteMoradorRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(ClienteMorador cliente)
	{ 
		salvaAtributosCliente(cliente);		
		cliente.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		clienteMoradorRepository.save(cliente);
	}
	
	public ClienteMorador salvaAtributosCliente(ClienteMorador cliente){
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
