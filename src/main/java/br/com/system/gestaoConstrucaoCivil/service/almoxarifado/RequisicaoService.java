package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Requisicao;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoService {

	@Autowired
	private RequisicaoRepository requisicaoRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoque;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(List<Requisicao> requisicao)
	{
	    for(int i  = 0 ; i < requisicao.size(); i++)
	    {
	    	requisicao.get(i).setDataSaida(LocalDate.now());
	    	estoque.baixarEstoque(requisicao.get(i).getProduto().getId(),requisicao.get(i).getQuantidadeSaida());
	    }
		 requisicaoRepository.save(requisicao);
	}
	public List<Requisicao> buscarTodos()
	{
		return requisicaoRepository.findAll();
	}
	public Requisicao buscarPorId(Long id)
    {
    	return requisicaoRepository.findOne(id);
    }
}
