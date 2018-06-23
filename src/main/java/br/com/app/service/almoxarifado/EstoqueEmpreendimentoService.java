package br.com.app.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Empreendimento;
import br.com.app.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.app.entity.almoxarifado.Fabricante;
import br.com.app.pojo.InformacaoEntradaProduto;
import br.com.app.pojo.MensagemException;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.almoxarifado.EstoqueEmpreendimentoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
@CacheConfig(cacheNames = "instruments")
public class EstoqueEmpreendimentoService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;
	@Autowired
	private NotaFiscalProdutoService notaProdutoService;

	@Transactional(readOnly = false)
	public void updateConfiguration(EstoqueEmpreendimento estoque) {
		
		if(estoque.getQuantidadeMinima() > estoque.getQuantidadeMaxima()) {
			throw new MensagemException("Quantidade mínma não pode ser maior que quantidade máxima, Favor refazer a operação");
		}
		
		estoqueRepository.save(estoque);
	}

	
	@Cacheable("instruments")
	public Page<EstoqueEmpreendimento> findAll(PageRequest pageRequest) {

		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();

		Page<EstoqueEmpreendimento> estoques = estoqueRepository
				.buscarTodosPorEmpreendimentoComPaginacao(idEmpreendimento, pageRequest);

		for (EstoqueEmpreendimento estoque : estoques) {
			InformacaoEntradaProduto info = notaProdutoService.getInformacaoProduto(estoque.getProduto().getId());
			estoque.setInforProduto(info);

		}

		return estoques;
	}
	
	public Page<EstoqueEmpreendimento> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		Page<EstoqueEmpreendimento> estoques = null;
		descricao = descricao.replaceAll("[./-]","");
		if (descricao.matches("[0-9]+")) {
			estoques = estoqueRepository.findByCodigoOrCodigoBarraEstoque(descricao, idEmpreendimento, page);
		}else {
			estoques = estoqueRepository.findByProdutoDescricaoIgnoreCaseContaining(descricao, page);
		}
		if(estoques == null || estoques.getNumberOfElements() < 1) {
			throw new MensagemException("Não foi encontrado nenhuma resultado para a busca" + descricao);
		}
		for (EstoqueEmpreendimento estoque : estoques) {
			InformacaoEntradaProduto info = notaProdutoService.getInformacaoProduto(estoque.getProduto().getId());
			estoque.setInforProduto(info);

		}
		return estoques;
	}

	public boolean existeProduto(Long id) {
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		return estoqueRepository.existeProduto(id, empreendimentoDoUsuario.getId());
	}

	/*public EstoqueEmpreendimento buscarPorCodigoOuCodigoBarraEstoque(String codigoOuCodigoBarra) {

		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();

		EstoqueEmpreendimento estoque = estoqueRepository.findByCodigoOrCodigoBarraEstoque(codigoOuCodigoBarra,
				idEmpreendimento);
		InformacaoEntradaProduto info = notaProdutoService.getInformacaoProduto(estoque.getProduto().getId());
		estoque.setInforProduto(info);
		return estoque;

	}*/

	public List<EstoqueEmpreendimento> produtoEstoqueBaixo() {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		return estoqueRepository.produtoEstoqueBaixo(idEmpreendimento);
	}

	public List<EstoqueEmpreendimento> produtoEstoqueAlto() {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		return estoqueRepository.produtoEstoqueAlto(idEmpreendimento);
	}


	public EstoqueEmpreendimento findById(Long id) {
		return estoqueRepository.findById(id).get();
	}

	
}
