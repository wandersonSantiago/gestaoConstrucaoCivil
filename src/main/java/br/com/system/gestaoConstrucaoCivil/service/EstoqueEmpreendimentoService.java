package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.ItemNotaFiscal;
import br.com.system.gestaoConstrucaoCivil.entity.NotaFiscalProduto;
import br.com.system.gestaoConstrucaoCivil.entity.Produto;
import br.com.system.gestaoConstrucaoCivil.repository.EstoqueEmpreendimentoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EstoqueEmpreendimentoService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;
	@Autowired
	private EmpreendimentoService empreendimento;

	@Transactional(readOnly = false)
	public void salvarOuEditar(EstoqueEmpreendimento produtoEstoque) {

		estoqueRepository.save(produtoEstoque);
	}

	@Transactional(readOnly = false)
	public void entradaEstoque(NotaFiscalProduto notaProduto) {
		
		notaProduto.getItens().forEach(item -> {

			updateEstoque(item);
		});
	}

	private void updateEstoque(ItemNotaFiscal item) {
		if (existeProduto(item.getProduto().getId())) {
			adicionarQuantidade(item.getProduto(), item.getQuantidade());
		} else {
			salvarOuEditar(criarNovoEstoque(item.getProduto(), item.getQuantidade()));
		}
	}

	private EstoqueEmpreendimento criarNovoEstoque(Produto produto, Integer quantidade) {
		EstoqueEmpreendimento estoque = new EstoqueEmpreendimento();
		estoque.adicionarProduto(produto);
		estoque.setQuantidade(quantidade);
		List<Empreendimento> empre = empreendimento.buscarTodos();
		estoque.setEmpreendimento(empre.get(0));
		return estoque;
	}

	@Transactional(readOnly = false)
	private void adicionarQuantidade(Produto produto, Integer quantidade) {
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(31L, produto.getId());
		estoque.setQuantidade(estoque.getQuantidade() + quantidade);
		salvarOuEditar(estoque);
	}

	@Transactional(readOnly = false)
	public void baixarEstoque(Long idProduto, Integer quantidade) {
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(31L, idProduto);
		estoque.setQuantidade(estoque.getQuantidade() - quantidade);
		salvarOuEditar(estoque);
	}

	public boolean existeProduto(Long id) {
		return estoqueRepository.existeProduto(id);
	}

}
