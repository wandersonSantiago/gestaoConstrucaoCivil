package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Produto;
import br.com.system.gestaoConstrucaoCivil.pojo.EntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.pojo.InformacaoEntradaProduto;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.EstoqueEmpreendimentoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EstoqueEmpreendimentoService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;
	@Autowired
	private NotaFiscalProdutoService notaProdutoService;
	
    @Transactional(readOnly = false)
	public void salvarOuEditar(EstoqueEmpreendimento produtoEstoque) {

		estoqueRepository.save(produtoEstoque);
	}
    public List<EstoqueEmpreendimento> buscarTodos(){
		
    	Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();  
    	
    	List<EstoqueEmpreendimento> estoques = estoqueRepository.buscarTodosPorEmpreendimento(idEmpreendimento);
    	
    	for(EstoqueEmpreendimento estoque: estoques) {
    		 InformacaoEntradaProduto info = notaProdutoService.getInformacaoProduto(estoque.getProduto().getId());
    		 estoque.setInforProduto(info);
    		
    	 }
    	
    	return estoques;
	}
	@Transactional(readOnly = false)
    public void entradaEstoque(EntradaOuBaixa entradaOutBaixa)
    {
        if (existeProduto(entradaOutBaixa.getProduto().getId())) {
			
			adicionarQuantidade(entradaOutBaixa.getProduto(),entradaOutBaixa.getQuantidade());
		} else {
			salvarOuEditar(criarNovoEstoque(entradaOutBaixa.getProduto(), entradaOutBaixa.getQuantidade()));
			
		}
           
    }
	@Transactional(readOnly = false)
	public void baixar(EntradaOuBaixa baixa) {
       
		
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(baixa.getEmpreendimento().getId(),baixa.getProduto().getId());
		
		estoque.setQuantidade(estoque.getQuantidade() - baixa.getQuantidade());
	    
		if(verificarEstoqueNegativo(estoque))
	    {
		  estoqueRepository.save(estoque);
	    }else
	    {
	    	throw new EstoqueEmpreendimentoException("Estoque negativo");
	    }
	}
	@Transactional(readOnly = false)
	public void baixar(List<EntradaOuBaixa> baixas) {
       
		
		for(EntradaOuBaixa baixa : baixas)
		{
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(baixa.getEmpreendimento().getId(),baixa.getProduto().getId());
		
		estoque.setQuantidade(estoque.getQuantidade() - baixa.getQuantidade());
	    
		if(verificarEstoqueNegativo(estoque))
	    {
		  estoqueRepository.save(estoque);
	    }else
	    {
	    	throw new EstoqueEmpreendimentoException("Estoque negativo");
	    }
		}
	}
	
	
	public boolean existeProduto(Long id) {
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		return estoqueRepository.existeProduto(id,empreendimentoDoUsuario.getId());
	}
   private EstoqueEmpreendimento criarNovoEstoque(Produto produto, Integer quantidade) {
		EstoqueEmpreendimento estoque = new EstoqueEmpreendimento();
		estoque.setProduto(produto);
		estoque.setQuantidade(quantidade);
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		estoque.setEmpreendimento(empreendimentoDoUsuario);
		return estoque;
	}

	@Transactional(readOnly = false)
	private void adicionarQuantidade(Produto produto, Integer quantidade) {
		
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(empreendimentoDoUsuario.getId(), produto.getId());
		estoque.setQuantidade(estoque.getQuantidade() + quantidade);
		salvarOuEditar(estoque);
	}
    
	
	private boolean verificarEstoqueNegativo(EstoqueEmpreendimento estoque)
	{
		if(estoque.getQuantidade() >= 0){
			return true;	
		}
		return false;
	}
	
	 public EstoqueEmpreendimento buscarPorCodigoOuCodigoBarraEstoque(String codigoOuCodigoBarra){
		   
	     Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		 return estoqueRepository.findByCodigoOrCodigoBarraEstoque(codigoOuCodigoBarra,idEmpreendimento);
	   }
}
