
package br.com.system.gestaoConstrucaoCivil.criarObjecto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Cotacao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresa;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresaItem;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoItem;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Requisicao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasaItem;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoItem;
import br.com.system.gestaoConstrucaoCivil.enuns.PerfilUsuarioEnum;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusCotacao;
import br.com.system.gestaoConstrucaoCivil.repository.AreaRepository;
import br.com.system.gestaoConstrucaoCivil.repository.CargoRepository;
import br.com.system.gestaoConstrucaoCivil.repository.CategoriaRepository;
import br.com.system.gestaoConstrucaoCivil.repository.DadoEmpresaRepository;
import br.com.system.gestaoConstrucaoCivil.repository.EmpreendimentoRepository;
import br.com.system.gestaoConstrucaoCivil.repository.EmpresaContratanteRepository;
import br.com.system.gestaoConstrucaoCivil.repository.EnderecoRepository;
import br.com.system.gestaoConstrucaoCivil.repository.FuncionarioRepository;
import br.com.system.gestaoConstrucaoCivil.repository.PessoaRepository;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.CotacaoEmpresaRepository;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.CotacaoRepository;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.FornecedorRepository;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.ProdutoRepository;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoRepository;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.PrestadoraServicoRepository;
import br.com.system.gestaoConstrucaoCivil.service.UsuarioService;

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
	@Autowired
	private CotacaoRepository cotacaoRepository;
	@Autowired
	private CotacaoEmpresaRepository cotacaoEmpresaRepository;
	@Autowired
	private UsuarioService usuarioService;
	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void cotacaoVencedora()
	{
		List<CotacaoEmpresa> lista = cotacaoEmpresaRepository.findbyId(2L);
		
		
        List<CotacaoEmpresaItem> itens = new ArrayList<CotacaoEmpresaItem>();
        
        for(CotacaoEmpresa cEmpresa: lista)
		{
			for(CotacaoEmpresaItem item : cEmpresa.getItens())
			{
		           itens.add(item);         
			}
		}
		
       
        Long idItem ;
        Double valor = 0.0;
     	
        while(itens.size() != 0)
     	{
     		idItem = itens.get(0).getItem().getId();
     		valor = itens.get(0).getValorUnitario();
     		
     		for(CotacaoEmpresaItem item : itens)
     		{
     		     if(idItem == item.getItem().getId())
     		     {
     		    	 if(valor < item.getValorUnitario())
     		    	 {
     		    		 item.setGanhou(false);
     		    	 }else
     		    	 {
     		    		 itens.get(0).setGanhou(false);
     		    		 
     		    		 
     		    	 }
     		     }
     		}
     		
     	}
		
		
		
	}
	
	public void criarUsuario()
	{
		Usuario user = new Usuario();
		user.setAtivo(true);
		user.setEmpreendimento(empreendimentoRepository.findAll().get(0));
		user.setLogin("josesilva");
		user.setEmail("josesilva@terra.com.br");
		user.setNome("Jose Silva");
		user.setSenha("898989");
		user.setPerfilsUsuario(Arrays.asList(PerfilUsuarioEnum.ADMIN,PerfilUsuarioEnum.ADMINISTRADOR_EMPRESA,PerfilUsuarioEnum.COMPRAS_CADASTRO));
		
		usuarioService.salvarOuEditar(user);
	}
	public void criarCotacaoEmpresa()
	{
		CotacaoEmpresa cotacaoEmpresa = new CotacaoEmpresa();
		Cotacao cotacao = cotacaoRepository.findOne(2L);
		cotacaoEmpresa.setCotacao(cotacao);
		cotacaoEmpresa.setFornecedor(fornecedorRepository.findOne(17L));
	
		CotacaoEmpresaItem item = new CotacaoEmpresaItem();
		item.setCotacaoEmpresa(cotacaoEmpresa);
		item.setObservaocao("OI2");
		item.setValorUnitario(8.31);
		item.setItem(cotacao.getItens().get(0));
	    
		
		CotacaoEmpresaItem item2 = new CotacaoEmpresaItem();
		item2.setCotacaoEmpresa(cotacaoEmpresa);
		item2.setObservaocao("hahha2");
		item2.setValorUnitario(87.1);
		item2.setItem(cotacao.getItens().get(1));
		cotacaoEmpresa.setItens(Arrays.asList(item,item2));
	
		cotacaoEmpresaRepository.save(cotacaoEmpresa);
	}
	
	public void criarCotacao()
	{
		Cotacao cotacao = new Cotacao();
		cotacao.setTema("Cotacao Teste");
		/*cotacao.setDataCriacao(LocalDate.now());
		cotacao.setDataLimite(LocalDate.now());
		*/cotacao.setEmpreendimento(empreendimentoRepository.findOne(31L));
		cotacao.setStatusCotacao(StatusCotacao.ABERTO);
		CotacaoItem item = new CotacaoItem();
		item.setContacao(cotacao);
		item.setDescricao("Produto teste");
		item.setQuantidade(30);
		
		
		CotacaoItem item2 = new CotacaoItem();
		item2.setContacao(cotacao);
		item2.setDescricao("Produto teste 2");
		item2.setQuantidade(10);
		
		
		
		cotacao.setItens(Arrays.asList(item,item2));
		cotacaoRepository.save(cotacao);
		
		
	}
	
	public void teste2()
	{
		Requisicao r = new Requisicao();
		r.setEmpreendimento(empreendimentoRepository.findAll().get(0));
	    
		
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


}
