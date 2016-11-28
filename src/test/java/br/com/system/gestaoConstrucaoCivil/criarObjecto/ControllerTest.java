
package br.com.system.gestaoConstrucaoCivil.criarObjecto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import br.com.system.gestaoConstrucaoCivil.GestaoConstrucaoCivilApplication;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoItem;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoEdificioItem;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Requisicao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasaItem;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusRequisicao;
import br.com.system.gestaoConstrucaoCivil.repository.AreaRepository;
import br.com.system.gestaoConstrucaoCivil.repository.CargoRepository;
import br.com.system.gestaoConstrucaoCivil.repository.CategoriaRepository;
import br.com.system.gestaoConstrucaoCivil.repository.DadoEmpresaRepository;
import br.com.system.gestaoConstrucaoCivil.repository.EmpreendimentoRepository;
import br.com.system.gestaoConstrucaoCivil.repository.EmpresaContratanteRepository;
import br.com.system.gestaoConstrucaoCivil.repository.EnderecoRepository;
import br.com.system.gestaoConstrucaoCivil.repository.FuncionarioRepository;
import br.com.system.gestaoConstrucaoCivil.repository.PessoaRepository;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.FornecedorRepository;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.ProdutoRepository;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoRepository;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.PrestadoraServicoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GestaoConstrucaoCivilApplication.class)
@WebIntegrationTest
public class ControllerTest {

	@Autowired
	private CargoRepository cargoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private DadoEmpresaRepository dadosEmpresaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private PrestadoraServicoRepository prestadoraServicoRepository;
	@Autowired
	private EmpreendimentoRepository empreendimentoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private ProdutoRepository protudoRepository;

	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private EmpresaContratanteRepository empresaContratanteRepository;
	@Autowired
	private RequisicaoRepository requisicaoRepository;
	
	private RestTemplate restTemplate = new TestRestTemplate();

	
	@Test 
	public void teste2()
	{
		Requisicao r = new Requisicao();
		r.setEmpreendimento(empreendimentoRepository.findAll().get(0));
	    r.setDataSaida(LocalDate.now());
		
	    /*RequisicaoEdificioItem itemEdificio = new RequisicaoEdificioItem();
		
		itemEdificio.setAndar(10);
		itemEdificio.setApartamento(14);
		itemEdificio.setTorre(3);
		
		itemEdificio.setProduto(protudoRepository.findOne(53L));
		itemEdificio.setQuantidade(100);
		itemEdificio.setValorUnitario(10.3);
		itemEdificio.setAreaProduto(areaRepository.findAll().get(0));
		itemEdificio.setRequisicao(r);*/
		
	    RequisicaoCasaItem itemCasa = new RequisicaoCasaItem();
	    
	    itemCasa.setAndar(2);
	    itemCasa.setCasa(10);
	    itemCasa.setProduto(protudoRepository.findOne(53L));
	    itemCasa.setQuantidade(200);
	    itemCasa.setValorUnitario(20.1);
	    itemCasa.setAreaProduto(areaRepository.findAll().get(0));
	    itemCasa.setRequisicao(r);
	    
		List<RequisicaoItem> listaItemRequisicao  = new ArrayList<>();
		
		//listaItemRequisicao.add(itemEdificio);
		listaItemRequisicao.add(itemCasa);
		r.setItem(listaItemRequisicao);
		
		requisicaoRepository.save(r);
      		
	}
	/*
	 * @Test public void testeCriarCategoriaAPI() throws JsonProcessingException
	 * {
	 * 
	 * Categoria categoria = new Categoria(); categoria.setDescricaoCategoria(
	 * "Descricao Categoria"); categoriaRepository.save(categoria);
	 * 
	 * }
	 */
  
	
	
	/*
	public void testeCriarFornecedor() throws JsonProcessingException {
		
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setAtivo(true);
		fornecedor.setContato("Jose Silva");
		
		DadoEmpresa dadosEmpresa = dadosEmpresaRepository.findOne(61L);
	    fornecedor.setDadosEmpresa(dadosEmpresa);
		fornecedor.setObservacao("NAO SEI");
		fornecedorRepository.save(fornecedor);
	}
	*/
	
/*	public void testeCriarProduto() throws JsonProcessingException {
		
		Produto produto = new Produto();
		produto.setAtivo(true);
		Categoria categoria = categoriaRepository.findOne(4L);
		
		produto.setCategoria(categoria);
		produto.setCodigoBarra("99999991");
		produto.setDescricao("cimento votoran");
		
		UnidadeMedida unidadeMedida =  unidadeRepository.findOne(109L);
		produto.setUnidadeMedida(unidadeMedida);
	    produto.setValorCompra(30000.0);
		List <Fornecedor> fornecedores = fornecedorRepository.findAll();
		produto.setFornecedores(fornecedores);
		
		protudoRepository.save(produto);
    	
	}*/
/*	@Test
	public void testeBuscarProduto() throws JsonProcessingException {
		
		Produto produto = protudoRepository.findOne(134L);
		for(Fornecedor fornecedor: produto.getFornecedores())
	       {
	           System.out.println("1 - " + produto.getDescricao());
	           System.out.println("Teste " + fornecedor.getContato());
	       }
	}*/
	/*@Test
	public void testeCriarFornecedor() throws JsonProcessingException {
		
		Imovel imovel = new Imovel();
		
		imovel.setAndar(10);
		
		imovelRepository.save(imovel);
	}*/
	/*@Test
	public void testeCriarFuncionario() throws JsonProcessingException {
	
		Empreendimento empreendimento = empreendimentoRepository.findOne(83L);
		
		System.out.println("Descricao " + empreendimento.getDescricao());
		System.out.println("Teste " + empreendimento.getEnderecoEmpreendimento().getBairro());
	}*/
	/*@Test
	public void testeCriarFuncionario() throws JsonProcessingException {
	
		Funcionario funcionario = new Funcionario();
	    funcionario.setNomeCompleto("Jose da Silva");
	    funcionario.setCpf("12111111111");
	    funcionario.setRg("231231231231");
	    funcionario.setIdade(69);
	    funcionario.setAtivo(true);
		
	    Endereco endereco = new Endereco(); 
		endereco.setRua("Rua Pau da Bandeira"); 
		endereco.setBairro("Bairro Tororó");
		endereco.setCidade("Campinas"); 
		endereco.setCep("132000-999");
		endereco.setNumero(1011); 
		endereco.setEstado("SP");
		
		enderecoRepository.save(endereco); 
	    funcionario.setEndereco(endereco);
	    
	    funcionario.setDataNascimento(new Date());
	    funcionario.setTelefoneCelular("3888888");
	    funcionario.setTelefoneFixo("8979878997");
	    Cargo cargo = new Cargo();
		cargo.setDescricao("aux");
		cargoRepository.save(cargo);
		funcionario.setCargo(cargo);
		funcionario.setCarteiraTrabalho(4545645);
		Date dataAdmissao = new Date();
		funcionario.setDataAdmissao(dataAdmissao);
		funcionario.setEmpreendimento(null);
		pessoaRepository.save(funcionario);
		
	}*/
	/*@Test
	public void testeCriarPrestadoraServico() throws JsonProcessingException {
	
		PrestadoraServico prestadoraServico = new PrestadoraServico();
		DadoEmpresa dadosEmpresa = new DadoEmpresa();
		dadosEmpresa.setNomeFantasia("Empresa Eng");
		dadosEmpresa.setCnpj("90.888.111/1003-21");
		dadosEmpresa.setInscricaoEstadual("9988.122.567.748");
		dadosEmpresa.setRazaoSocial("Empresa Eng LTDA");
		dadosEmpresa.setEmail("eng@terra.com");
		dadosEmpresa.setTelefone("1938710076");
		Endereco endereco = new Endereco(); 
		endereco.setRua("Rua 100"); 
		endereco.setBairro("Bairro da Lua");
		endereco.setCidade("Campinas"); 
		endereco.setCep("132110-785");
		endereco.setNumero(41); 
		endereco.setEstado("SP");
		
		enderecoRepository.save(endereco); 
        dadosEmpresa.setEndereco(endereco);
        dadosEmpresaRepository.save(dadosEmpresa);
	    
        prestadoraServico.setDadoEmpresa(dadosEmpresa);
        prestadoraServicoRepository.save(prestadoraServico);
        
		
	}*/

	

}
