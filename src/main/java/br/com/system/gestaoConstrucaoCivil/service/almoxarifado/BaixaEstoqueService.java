package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.BaixaEstoque;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.BaixaEstoqueRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BaixaEstoqueService {

	@Autowired
	private BaixaEstoqueRepository baixaEstoqueRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoque;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(List<BaixaEstoque> baixasEstoque)
	{
	    for(int i  = 0 ; i < baixasEstoque.size(); i++)
	    {
	    	baixasEstoque.get(i).setDataSaida(LocalDate.now());
	    	estoque.baixarEstoque(baixasEstoque.get(i).getProduto().getId(),baixasEstoque.get(i).getQuantidadeSaida());
	    }
		 baixaEstoqueRepository.save(baixasEstoque);
	}
	public List<BaixaEstoque> buscarTodos()
	{
		return baixaEstoqueRepository.findAll();
	}
	public BaixaEstoque buscarPorId(Long id)
    {
    	return baixaEstoqueRepository.findOne(id);
    }
}
