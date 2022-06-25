package br.com.app.estoque.domain.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.commons.domain.model.Empreendimento;
import br.com.app.estoque.domain.model.EntradaOuBaixa;
import br.com.app.estoque.domain.model.EstoqueEmpreendimento;
import br.com.app.estoque.domain.model.IItem;
import br.com.app.estoque.domain.model.Produto;
import br.com.app.estoque.domain.repository.EstoqueEmpreendimentoRepository;
import br.com.app.infraestructure.security.SessionUsuario;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EntradaEstoqueService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = false)
	public void entradaEstoque(EntradaOuBaixa entrada) {
		Collection<IItem> t = entrada.getItens();

		for (IItem item : t) {
			if (estoqueRepository.existeProduto(item.getProduto().getId(), entrada.empreendimentoEntrada().getId())) {
				adicionarQuantidade(item.getProduto(), item.getQuantidade(), entrada.empreendimentoEntrada().getId());
			} else {

				estoqueRepository.save(criarNovoEstoque(item.getProduto(), item.getQuantidade()));

			}

		}
	}

	private void adicionarQuantidade(Produto produto, Integer quantidade, Long idEmpreendimento) {

		EstoqueEmpreendimento estoque = estoqueRepository.estoque(idEmpreendimento, produto.getId());
		estoque.setQuantidade(estoque.getQuantidade() + quantidade);
		estoqueRepository.save(estoque);
	}

	private EstoqueEmpreendimento criarNovoEstoque(Produto produto, Integer quantidade) {
		EstoqueEmpreendimento estoque = new EstoqueEmpreendimento();
		estoque.setProduto(produto);
		estoque.setQuantidade(quantidade);
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		estoque.setEmpreendimento(empreendimentoDoUsuario);
		return estoque;
	}
}
