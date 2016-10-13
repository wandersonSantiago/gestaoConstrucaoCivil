package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.BaixaEstoqueCasa;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.BaixaEstoqueCasaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BaixaEstoqueCasaService {

	@Autowired
	private BaixaEstoqueCasaRepository baixaEstoqueCasaRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(List<BaixaEstoqueCasa> baixasEstoqueCasa)
	{
	   
	    baixaEstoqueCasaRepository.save(baixasEstoqueCasa);
	}
	public List<BaixaEstoqueCasa> buscarTodos()
	{
		return baixaEstoqueCasaRepository.findAll();
	}
	public BaixaEstoqueCasa buscarPorId(Long id)
    {
    	return baixaEstoqueCasaRepository.findOne(id);
    }
	
}
