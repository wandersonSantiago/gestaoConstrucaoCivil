package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.EstoqueEmpreendimento;
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
    
    public Page<EstoqueEmpreendimento> buscarTodosComPaginacao(PageRequest pageRequest){
		
    	Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();  
    	
    	System.out.println("INICIO");
    	long tempoInicio = System.currentTimeMillis();
    	
    	Page<EstoqueEmpreendimento> estoques = estoqueRepository.buscarTodosPorEmpreendimentoComPaginacao(idEmpreendimento , pageRequest);
    	
    	for(EstoqueEmpreendimento estoque: estoques) {
    		 InformacaoEntradaProduto info = notaProdutoService.getInformacaoProduto(estoque.getProduto().getId());
    		 estoque.setInforProduto(info);
    		
    	 }
    	System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));
    	return estoques;
	}
	
	public boolean existeProduto(Long id) {
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		return estoqueRepository.existeProduto(id,empreendimentoDoUsuario.getId());
	}
 
	
	 public EstoqueEmpreendimento buscarPorCodigoOuCodigoBarraEstoque(String codigoOuCodigoBarra){
		   
		 Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		 
		 EstoqueEmpreendimento estoque =  estoqueRepository.findByCodigoOrCodigoBarraEstoque(codigoOuCodigoBarra,idEmpreendimento);
		 InformacaoEntradaProduto info = notaProdutoService.getInformacaoProduto(estoque.getProduto().getId());
	     estoque.setInforProduto(info);
	     return  estoque;
	 
	 }
	public List<EstoqueEmpreendimento> produtoEstoqueBaixo() {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		return estoqueRepository.produtoEstoqueBaixo(idEmpreendimento);
	}
	public List<EstoqueEmpreendimento> produtoEstoqueAlto() {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		return estoqueRepository.produtoEstoqueAlto(idEmpreendimento);
	}
	public Page<EstoqueEmpreendimento> findAll(PageRequest pageRequest) {
		return estoqueRepository.findAll(pageRequest);
	}
}
