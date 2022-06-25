package br.com.app.estoque.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.commons.domain.model.Empreendimento;
import br.com.app.estoque.domain.model.EstoqueEmpreendimento;
import br.com.app.estoque.domain.repository.EstoqueEmpreendimentoRepository;
import br.com.app.infraestructure.exception.MensagemException;
import br.com.app.infraestructure.security.SessionUsuario;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
//@CacheConfig(cacheNames = "instruments")
public class EstoqueEmpreendimentoService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;
	@Autowired
	private CalculaEstoqueService calculaEstoques;
	
	@Transactional(readOnly = false)
	public void updateConfiguration(EstoqueEmpreendimento estoque) {

		if (estoque.getQuantidadeMinima() > estoque.getQuantidadeMaxima()) {
			throw new MensagemException(
					"Quantidade mínma não pode ser maior que quantidade máxima, Favor refazer a operação");
		}

		estoqueRepository.save(estoque);
	}

	/* @Cacheable("instruments") */
	public Page<EstoqueEmpreendimento> findAll(PageRequest pageRequest) {

		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();

		Page<EstoqueEmpreendimento> estoques = estoqueRepository
				.buscarTodosPorEmpreendimentoComPaginacao(idEmpreendimento, pageRequest);
		List<EstoqueEmpreendimento> estoquesCalculado = calculaEstoques.calcular(estoques.getContent());
		 
		
		return new PageImpl<>(estoquesCalculado);
	}

	public Page<EstoqueEmpreendimento> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		Page<EstoqueEmpreendimento> estoques = null;
		descricao = descricao.replaceAll("[./-]", "");

		if (isCodigoOrCodigoBarra(descricao)) {
			estoques = estoqueRepository.findByCodigoOrCodigoBarraEstoque(descricao, idEmpreendimento, page);
		} else {
			estoques = estoqueRepository.findByProdutoDescricaoIgnoreCaseContainingAndEmpreendimentoId(descricao,
					idEmpreendimento, page);
		}
		foundEstoque(estoques, descricao);
		List<EstoqueEmpreendimento> estoquesCalculado = calculaEstoques.calcular(estoques.getContent());
		
		return new PageImpl<>(estoquesCalculado);
		
		 
	}

	private Boolean isCodigoOrCodigoBarra(String descricao) {
		return descricao.matches("[0-9]+");
	}

	private void foundEstoque(Page<EstoqueEmpreendimento> estoques, String descricao) {
		if (estoques == null || estoques.getNumberOfElements() < 1) {
			throw new MensagemException("Não foi encontrado nenhuma resultado para a busca" + descricao);
		}

	}
 
	public boolean existeProduto(Long id) {
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		return estoqueRepository.existeProduto(id, empreendimentoDoUsuario.getId());
	}

	 
	public List<EstoqueEmpreendimento> produtoEstoqueBaixo() {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		return estoqueRepository.produtoEstoqueBaixo(idEmpreendimento);
	}

	public List<EstoqueEmpreendimento> produtoEstoqueAlto() {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		return estoqueRepository.produtoEstoqueAlto(idEmpreendimento);
	}

	public EstoqueEmpreendimento findById(Long id) {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		
		return estoqueRepository.findByIdAndEmpreendimentoId(id, idEmpreendimento);
	}

}
