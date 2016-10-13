package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.BaixaEstoqueEdificio;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.BaixaEstoqueEdificioRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BaixaEstoqueEdificioService {

	@Autowired
	private BaixaEstoqueEdificioRepository baixaEstoqueEdificioRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(List<BaixaEstoqueEdificio> baixasEstoqueEdificio)
	{
	   
	    baixaEstoqueEdificioRepository.save(baixasEstoqueEdificio);
	}
	
	public List<BaixaEstoqueEdificio> buscarTodos()
	{
		return baixaEstoqueEdificioRepository.findAll();
	}
	public BaixaEstoqueEdificio buscarPorId(Long id)
    {
    	return baixaEstoqueEdificioRepository.findOne(id);
    }
}
