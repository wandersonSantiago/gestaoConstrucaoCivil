package br.com.app.service.almoxarifado;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.almoxarifado.Produto;
import br.com.app.repository.almoxarifado.ProdutoRepository;
import br.com.app.util.gerador.codigo.GeraCodigoProduto;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
    @Autowired
    private GeraCodigoProduto gerar;
	
    public List<Produto> buscarTodos() {

    	return produtoRepository.buscarTodos();
	}
    public List<Produto> buscarPorDescricao(String descricao)
    {
    	return null;
    }
	public Optional<Produto> buscaPorId(Long id) {

		return produtoRepository.findById(id);
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
