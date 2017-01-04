package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoCasa;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.ServicoCasaRepository;

@Service
@Transactional(readOnly = true)
public class ServicoCasaService {

	@Autowired
	private ServicoCasaRepository servicoCasaRepository;
	
	public List<ServicoCasa> buscarTodos() {
		
		return servicoCasaRepository.findAll();
	}
	@Transactional(readOnly = false)
	public void  salvarOuEditar(ServicoCasa servico)
	{
		servicoCasaRepository.save(servico);
	}
	public ServicoCasa buscarPorId(Long id) {
		return servicoCasaRepository.findOne(id);
	}
	
}
