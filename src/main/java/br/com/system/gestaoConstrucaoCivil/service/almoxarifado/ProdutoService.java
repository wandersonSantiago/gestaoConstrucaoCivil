package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Produto;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.ProdutoRepository;
import br.com.system.gestaoConstrucaoCivil.util.geradorCodigo.GeraCodigoProduto;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
    @Autowired
    private GeraCodigoProduto gerar;
	
    public List<Produto> buscarTodos() {

	 //	return produtoRepository.findAll();
    	return produtoRepository.buscarTodos();
	}
    public List<Produto> buscarPorDescricao(String descricao)
    {
    	return null;
    }
	public Produto buscaPorId(Long id) {

		return produtoRepository.findOne(id);
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(Produto produto) {
		
		
		produto.setCodigo(gerar.gerarCodigoProduto());
		
		if(produto.isGeraCodigoBarra())
		{
		    produto.setCodigoBarra(gerar.gerarCodigoBarra());
		}
		produtoRepository.save(produto);

	}
   public Produto buscarPorCodigoOuCodigoBarra(String codigoOuCodigoBarra) {

	   return produtoRepository.findByCodigoOrCodigoBarra(codigoOuCodigoBarra);
   }
   public boolean existeCodigo(Integer codigo) {
		return produtoRepository.existeCodigo(codigo);
	}

	public boolean existeCodigoBarra(String codigoBarra) {
		return produtoRepository.existeCodigoBarra(codigoBarra);
	}
	public Page<Produto> buscarTodosComPaginacao(PageRequest pageRequest) {
		return produtoRepository.findAll(pageRequest);
	}
}
