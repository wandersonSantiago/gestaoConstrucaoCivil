package br.com.app.financeiro.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.estoque.domain.service.BaixaEstoqueService;
import br.com.app.estoque.domain.service.EntradaEstoqueService;
import br.com.app.financeiro.domain.model.NotaFiscalProduto;
import br.com.app.financeiro.domain.repository.NotaFiscalProdutoRepository;
import br.com.app.infraestructure.exception.MensagemException;
import br.com.app.infraestructure.security.SessionUsuario;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class NotaFiscalProdutoService {

	@Autowired
	private NotaFiscalProdutoRepository notaFiscalProdutoRepository;
	@Autowired
	private EntradaEstoqueService entradaEstoque;
	@Autowired
	private BaixaEstoqueService baixarEstoque;
	@Autowired
	private GastoEmpreendimentoService gastoEmpreendimento;
	
  	@Transactional(readOnly = false)
	public void salvarOuEditar(NotaFiscalProduto notaFiscalProduto) {

		notaFiscalProduto.novaNotaProduto();
		notaFiscalProdutoRepository.save(notaFiscalProduto);
		gastoEmpreendimento.adicionarValorGasto(notaFiscalProduto.getNotaFiscal().getValorTotal());
	    entradaEstoque.entradaEstoque(notaFiscalProduto);
      
     }

	@Transactional(readOnly = false)
	public void cancelarEntrada(Long numeroEntrada)
	{
		NotaFiscalProduto nota = notaFiscalProdutoRepository.buscarPorNumero(numeroEntrada);
		baixarEstoque.baixar(nota);
		gastoEmpreendimento.removerValorGasto(nota.getNotaFiscal().getValorTotal());
		nota.getNotaFiscal().cancelarNota();
		notaFiscalProdutoRepository.save(nota);
	}

	public Page<NotaFiscalProduto> findAll(PageRequest page) {
		return notaFiscalProdutoRepository.findAll(page);
	}

	public Page<NotaFiscalProduto> findByCodigo(String descricao, Pageable page) {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		Page<NotaFiscalProduto> estoques = null;
		descricao = descricao.replaceAll("[./-]", "");

			estoques = notaFiscalProdutoRepository.findByNotaFiscalNumeroAndNotaFiscalEmpreendimentoId(Long.valueOf(descricao),
					idEmpreendimento, page);
			
			if(estoques.getNumberOfElements() < 1) {
				throw new MensagemException("Nota fiscal não encontrada, com o código: " +  descricao);
			}
			
		return estoques;
	}

	public Optional<NotaFiscalProduto> findById(Long id) {
		return notaFiscalProdutoRepository.findById(id);
	}

}
