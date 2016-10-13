package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.BaixaEstoque;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.ItemNotaFiscal;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscalProduto;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Produto;
import br.com.system.gestaoConstrucaoCivil.pojo.InformacaoEntradaProduto;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.EstoqueEmpreendimentoRepository;
import br.com.system.gestaoConstrucaoCivil.service.EmpreendimentoService;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EstoqueEmpreendimentoService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;
	@Autowired
	private EmpreendimentoService empreendimento;
	@Autowired
	private NotaFiscalProdutoService notaProdutoService;
	//Remover depois isso
	@Autowired
    EmpreendimentoService serviceEmpr;
	//
    
    @Transactional(readOnly = false)
	public void salvarOuEditar(EstoqueEmpreendimento produtoEstoque) {

		estoqueRepository.save(produtoEstoque);
	}
    public List<EstoqueEmpreendimento> buscarTodos(){
		
    	 List<EstoqueEmpreendimento> estoques = estoqueRepository.findAll();
         
    	 for(int i = 0 ; i < estoques.size(); i++){
    		 
    		 InformacaoEntradaProduto info = notaProdutoService.getInformacaoProduto(estoques.get(i).getProduto().getId());
    		 estoques.get(i).setInforProduto(info);
    		 System.out.println("VALOR medio" + estoques.get(i).getCustoMedio());
    	 }
    	return estoques;
	}
	@Transactional(readOnly = false)
	public void entradaEstoque(NotaFiscalProduto notaProduto) {
		
		notaProduto.getItens().forEach(item -> {

		updateEstoque(item);
		});
	}

	private void updateEstoque(ItemNotaFiscal item) {
		
		System.out.println("Entrou no update");
		if (existeProduto(item.getProduto().getId())) {
			
			adicionarQuantidade(item.getProduto(), item.getQuantidade());
		} else {
			salvarOuEditar(criarNovoEstoque(item.getProduto(), item.getQuantidade()));
			
		}
	}

	private EstoqueEmpreendimento criarNovoEstoque(Produto produto, Integer quantidade) {
		EstoqueEmpreendimento estoque = new EstoqueEmpreendimento();
		estoque.setProduto(produto);
		estoque.setQuantidade(quantidade);
		List<Empreendimento> empre = empreendimento.buscarTodos();
		estoque.setEmpreendimento(empre.get(0));
		return estoque;
	}

	@Transactional(readOnly = false)
	private void adicionarQuantidade(Produto produto, Integer quantidade) {
		
		// Pega o primeiro empreenidmento
	    //remover depois
		Empreendimento empr = serviceEmpr.buscarTodos().get(0);
		//
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(empr.getId(), produto.getId());
		
		estoque.setQuantidade(estoque.getQuantidade() + quantidade);
		salvarOuEditar(estoque);
	}

	@Transactional(readOnly = false)
	public void baixarEstoque(Long idProduto, Integer quantidade) {
		
		// Pega o primeiro empreenidmento
	    //remover depois
		Empreendimento empr = serviceEmpr.buscarTodos().get(0);
		//
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(empr.getId() ,idProduto);
		estoque.setQuantidade(estoque.getQuantidade() - quantidade);
	    salvarOuEditar(estoque);
	}
	@Transactional(readOnly = false)
	public void baixarEstoque(BaixaEstoque baixaEstoque){
		
		// Pega o primeiro empreenidmento
	    //remover depois
		Empreendimento empr = serviceEmpr.buscarTodos().get(0);
		//
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(empr.getId() ,baixaEstoque.getProduto().getId());
		estoque.setQuantidade(estoque.getQuantidade() - baixaEstoque.getQuantidadeSaida());
	    salvarOuEditar(estoque);
		    
		
	}
	public boolean existeProduto(Long id) {
		return estoqueRepository.existeProduto(id);
	}
   
}
