package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.BaixaEstoqueOutros;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.BaixaEstoqueOutrosRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BaixaEstoqueOutrosService {

	
	@Autowired
	private BaixaEstoqueOutrosRepository baixaEstoqueOutrosRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoque;
	
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(List<BaixaEstoqueOutros> baixasEstoqueOutros)
	{
	    for(BaixaEstoqueOutros baixa: baixasEstoqueOutros){
	    	
	    	estoque.baixarEstoque(baixa);
	    }
		baixaEstoqueOutrosRepository.save(baixasEstoqueOutros);
	}
	public List<BaixaEstoqueOutros> buscarTodos()
	{
		return baixaEstoqueOutrosRepository.findAll();
	}
	public BaixaEstoqueOutros buscarPorId(Long id)
    {
    	return baixaEstoqueOutrosRepository.findOne(id);
    }
}
