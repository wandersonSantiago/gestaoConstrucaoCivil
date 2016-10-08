package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscalProduto;
import br.com.system.gestaoConstrucaoCivil.pojo.InformacaoEntradaProduto;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.NotaFiscalProdutoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class NotaFiscalProdutoService {

	@Autowired
	private NotaFiscalProdutoRepository notaFiscalProdutoRepository;
    @Autowired
    private EstoqueEmpreendimentoService estoque;
    
    public List<NotaFiscalProduto> buscarTodos() {

		return notaFiscalProdutoRepository.findAll();
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(NotaFiscalProduto notaFiscalProduto) {
		
		adicionarNotaProdutoItens(notaFiscalProduto);
		notaFiscalProdutoRepository.save(notaFiscalProduto);
		estoque.entradaEstoque(notaFiscalProduto);
		
	}

	public NotaFiscalProduto buscarPorId(Long id) {

		return notaFiscalProdutoRepository.findOne(id);
	}
	
	private void adicionarNotaProdutoItens(NotaFiscalProduto nota)
	{
		for(int i = 0 ; i < nota.getItens().size() ; i++)
		{
			nota.getItens().get(i).setNotaFiscalProduto(nota);
		}
	}
	public InformacaoEntradaProduto getInfoProduto(Long idProduto){
		
		return gerarInformacao(idProduto);
	}
    private InformacaoEntradaProduto gerarInformacao(Long idProduto){
		
	    List<NotaFiscalProduto> nota = notaFiscalProdutoRepository.buscarNota();
	    Double valorTotal = 0.0;
	    Integer quantidadeTotal = 0;
	    for(int i = 0 ; i < nota.size(); i++){
			 
	    	 for(int p = 0 ; p < nota.get(i).getItens().size(); p++)
	    	 {
	    		if(nota.get(i).getItens().get(p).getProduto().getId() == idProduto)
	    		{
	    		valorTotal += nota.get(i).getItens().get(p).getValorTotal();
	    		quantidadeTotal += nota.get(i).getItens().get(p).getQuantidade();
	    	 }
	    	 }
	     }
	   InformacaoEntradaProduto  informacao = new InformacaoEntradaProduto (valorTotal,quantidadeTotal);
	   return  informacao;
    }
}
