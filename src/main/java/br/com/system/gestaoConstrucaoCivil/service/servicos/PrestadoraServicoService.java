package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.PrestadoraServico;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.PrestadoraServicoRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class PrestadoraServicoService {

	@Autowired
	private PrestadoraServicoRepository prestadoraServicoRepository;
	
	 public List<PrestadoraServico> buscarTodos() {
	 		
	 		return prestadoraServicoRepository.findAll();
	 	}
	 
	 
	 @Transactional(readOnly = false)
	 public void salvarOuEditar(PrestadoraServico prestadoraServico)
	 {
		 prestadoraServicoRepository.save(prestadoraServico);
	 }
	 
	 	 
	 public PrestadoraServico buscarPorId(Long id){
		 
		 return prestadoraServicoRepository.findOne(id);
	 }
}
