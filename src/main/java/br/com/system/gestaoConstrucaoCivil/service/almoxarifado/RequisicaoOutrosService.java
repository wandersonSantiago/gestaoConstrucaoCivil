package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoOutros;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoOutrosRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoOutrosService {

	
	@Autowired
	private RequisicaoOutrosRepository requisicaoOutrosRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoque;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(List<RequisicaoOutros> requisicaoOutros)
	{
	    for(RequisicaoOutros requisicao: requisicaoOutros){
	    	requisicao.setDataSaida(LocalDate.now());
	    	estoque.baixarEstoque(requisicao);
	    }
		requisicaoOutrosRepository.save(requisicaoOutros);
	}
	public List<RequisicaoOutros> buscarTodos()
	{
		return requisicaoOutrosRepository.findAll();
	}
	public RequisicaoOutros buscarPorId(Long id)
    {
    	return requisicaoOutrosRepository.findOne(id);
    }
}
