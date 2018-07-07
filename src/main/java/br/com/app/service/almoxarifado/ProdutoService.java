package br.com.app.service.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.almoxarifado.Produto;
import br.com.app.exceptions.NotFoundException;
import br.com.app.pojo.MensagemException;
import br.com.app.repository.almoxarifado.ProdutoRepository;
import br.com.app.util.gerador.codigo.ExecuteGera;
import br.com.app.util.gerador.codigo.Gera;
import br.com.app.util.gerador.codigo.GeraCodigoProduto;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ExecuteGera execute;

	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Produto produto) {

		Gera  gerarCodigoProduto = new GeraCodigoProduto();
		
		String codigo = execute.gerar(gerarCodigoProduto);
		produto.setAtivo(true);
		produto.setCodigo(Integer.valueOf(codigo));
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

	public Page<Produto> findAll(PageRequest page) {
		return produtoRepository.findAll(page);
	}
	
	public Produto buscaPorId(Long id) {
		return  produtoRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
	}
	
	public Page<Produto> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Page<Produto> list = null;
		descricao = descricao.replaceAll("[./-]","");
		if (descricao.matches("[0-9]+")) {
			list = produtoRepository.findByCodigo(descricao, page);
		}else {
			list = produtoRepository.findByDescricaoIgnoreCaseContaining(descricao, page);
		}
		if(list == null || list.getNumberOfElements() < 1) {
			throw new MensagemException("Não foi encontrado nenhuma resultado para a busca" + descricao);
		}
		return list;
	}
}
