package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

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
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
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
	
    @Transactional(readOnly = false)
	public void salvarOuEditar(EstoqueEmpreendimento produtoEstoque) {

		estoqueRepository.save(produtoEstoque);
	}
    public List<EstoqueEmpreendimento> buscarTodos(){
		
    	 List<EstoqueEmpreendimento> estoques = estoqueRepository.findAll();
         
    		for(EstoqueEmpreendimento estoque: estoques) {
    		 InformacaoEntradaProduto info = notaProdutoService.getInformacaoProduto(estoque.getProduto().getId());
    		 estoque.setInforProduto(info);
    		
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
		
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(empreendimentoDoUsuario.getId(), produto.getId());
		estoque.setQuantidade(estoque.getQuantidade() + quantidade);
		salvarOuEditar(estoque);
	}

	@Transactional(readOnly = false)
	public void baixarEstoque(Long idProduto, Integer quantidade) {
		
	    Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(empreendimentoDoUsuario.getId() ,idProduto);
		estoque.setQuantidade(estoque.getQuantidade() - quantidade);
	    salvarOuEditar(estoque);
	   
	}
	@Transactional(readOnly = false)
	public void baixarEstoque(BaixaEstoque baixaEstoque) {
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(empreendimentoDoUsuario.getId() ,baixaEstoque.getProduto().getId());
		
		estoque.setQuantidade(estoque.getQuantidade() - baixaEstoque.getQuantidadeSaida());
	    
		if(verificarEstoqueNegativo(estoque))
	    {
		  salvarOuEditar(estoque);
	    }else
	    {
	    	throw new EstoqueEmpreendimentoException("Estoque negativo");
	    }
	}
	private boolean verificarEstoqueNegativo(EstoqueEmpreendimento estoque)
	{
		if(estoque.getQuantidade() >= 0){
			return true;	
		}
		return false;
	}
	public boolean existeProduto(Long id) {
		return estoqueRepository.existeProduto(id);
	}
	 public Produto buscarPorCodigoOuCodigoBarra(String codigo) {

			if (codigo.length() == 13) {
				return estoqueRepository.findByCodigoBarra(codigo);
			} else {
				return estoqueRepository.findByCodigo(Integer.valueOf(codigo));
			}
		}

   
}
