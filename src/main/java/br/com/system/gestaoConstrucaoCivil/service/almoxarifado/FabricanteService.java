package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Fabricante;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.FabricanteRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class FabricanteService {

	@Autowired
	private FabricanteRepository  fabricanteRepository;
	
	public List<Fabricante> buscarTodos() {

		return fabricanteRepository.findAll();
	}
	@Transactional(readOnly = false)
	public void salvarOuEditar(Fabricante fabricante)
	{
		fabricanteRepository.save(fabricante);
	}
	public Fabricante buscarPorId(Long id) {
		
		return fabricanteRepository.findOne(id);
	}
}
