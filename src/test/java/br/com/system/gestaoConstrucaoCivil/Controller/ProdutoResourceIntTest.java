package br.com.system.gestaoConstrucaoCivil.Controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.GestaoConstrucaoCivilApplication;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Produto;
import br.com.system.gestaoConstrucaoCivil.enuns.UnidadeMedidaEnum;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.ProdutoRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GestaoConstrucaoCivilApplication.class)
public class ProdutoResourceIntTest {

	@Autowired
	private ProdutoRepository produtoRepository;


	
	private static final Boolean DEFAULT_ATIVO = false;
	private static final Boolean UPDATED_ATIVO = true;

	private static final Integer DEFAULT_CODIGO = 1;
	private static final Integer UPDATED_CODIGO = 2;

	private static final String DEFAULT_CODIGO_BARRA = "AAAAAAAAAA";
	private static final String UPDATED_CODIGO_BARRA = "BBBBBBBBBB";

	private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
	private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

	private static final UnidadeMedidaEnum DEFAULT_UNIDADE_MEDIDA = UnidadeMedidaEnum.UNIDADE;
	private static final UnidadeMedidaEnum UPDATED_UNIDADE_MEDIDA = UnidadeMedidaEnum.METRO;



	private Produto produto;
	
	public Produto criarEntity() {

		Produto produto = new Produto();
		produto.setAtivo(DEFAULT_ATIVO);
		produto.setCodigo(DEFAULT_CODIGO);
		produto.setCodigoBarra(DEFAULT_CODIGO_BARRA);
		produto.setDescricao(DEFAULT_DESCRICAO);
		produto.setUnidadeMedida(DEFAULT_UNIDADE_MEDIDA);
		return produto;
	}
	@Before
	public void initTest() {

		this.produto = criarEntity();
	}

	@Test
	@Transactional
	public void salvar() {

		produtoRepository.save(produto);
		
	    List<Produto> produtos = produtoRepository.findAll();
	        
	        Produto testProduto = produtos.get(produtos.size() - 1);
	        assertThat(testProduto.isAtivo()).isEqualTo(DEFAULT_ATIVO);
	        assertThat(testProduto.getCodigo()).isEqualTo(DEFAULT_CODIGO);
	        assertThat(testProduto.getCodigoBarra()).isEqualTo(DEFAULT_CODIGO_BARRA);
	        assertThat(testProduto.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
	        assertThat(testProduto.getUnidadeMedida()).isEqualTo(DEFAULT_UNIDADE_MEDIDA);
	        
	}
	
	@Test
    @Transactional
    public void updateProduto() throws Exception
    {
		produtoRepository.save(produto);
		
		Produto produtoEditado = produtoRepository.findById(produto.getId()).get();
		
		produtoEditado.setAtivo(UPDATED_ATIVO);
		produtoEditado.setCodigo(UPDATED_CODIGO);
		produtoEditado.setCodigoBarra(UPDATED_CODIGO_BARRA);
		produtoEditado.setDescricao(UPDATED_DESCRICAO);
		produtoEditado.setUnidadeMedida(UPDATED_UNIDADE_MEDIDA);
        
		produtoRepository.saveAndFlush(produtoEditado);	
	    
	    List<Produto> produtos = produtoRepository.findAll();
        
        Produto testProduto = produtos.get(produtos.size() - 1);
        assertThat(testProduto.isAtivo()).isEqualTo(UPDATED_ATIVO);
        assertThat(testProduto.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testProduto.getCodigoBarra()).isEqualTo(UPDATED_CODIGO_BARRA);
        assertThat(testProduto.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testProduto.getUnidadeMedida()).isEqualTo(UPDATED_UNIDADE_MEDIDA);
    }
	
	
	
	
}
