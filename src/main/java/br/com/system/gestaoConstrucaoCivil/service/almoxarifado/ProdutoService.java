package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Produto;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.ProdutoRepository;
import br.com.system.gestaoConstrucaoCivil.util.ProdutoUtil;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> buscarTodos() {

		return produtoRepository.findAll();
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
		
		ProdutoUtil gerar = new ProdutoUtil();
		produto.setCodigo(gerar.gerarCodigo());
		
		if(produto.isGeraCodigoBarra())
		{
		    produto.setCodigoBarra(gerar.gerarCodigoBarra(produto.getCodigo()));
		}
		produtoRepository.save(produto);

	}
   public Produto buscarPorCodigoOuCodigoBarra(String codigo) {

		if (codigo.length() == 13) {
			return produtoRepository.findByCodigoBarra(codigo);
		} else {
			return produtoRepository.findByCodigo(Integer.valueOf(codigo));
		}
	}

	public boolean existeCodigo(Integer codigo) {
		return produtoRepository.existeCodigo(codigo);
	}

	public boolean existeCodigoBarra(String codigoBarra) {
		return produtoRepository.existeCodigoBarra(codigoBarra);
	}
}
