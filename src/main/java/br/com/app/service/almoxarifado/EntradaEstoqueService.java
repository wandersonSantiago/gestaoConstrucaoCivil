package br.com.app.service.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Empreendimento;
import br.com.app.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.app.entity.almoxarifado.Produto;
import br.com.app.entity.almoxarifado.interfaces.EntradaOuBaixa;
import br.com.app.entity.almoxarifado.interfaces.IItem;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.almoxarifado.EstoqueEmpreendimentoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EntradaEstoqueService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;

	@Transactional(readOnly = false)
	public void entradaEstoque(EntradaOuBaixa entrada) {
		Collection<IItem> t = entrada.getItens();

		for (IItem item : t) {
			if (estoqueRepository.existeProduto(item.getProduto().getId(), entrada.empreendimentoEntrada().getId())) {
				adicionarQuantidade(item.getProduto(), item.getQuantidade(), entrada.empreendimentoEntrada().getId());
			} else {

				//testar depois isso
				/*EstoqueEmpreendimento e = new EstoqueBuilder().produto(item.getProduto())
						.quantidade(item.getQuantidade()).build();*/
				estoqueRepository.save(criarNovoEstoque(item.getProduto(), item.getQuantidade()));

			}

		}
	}

	@Transactional(readOnly = false)
	private void adicionarQuantidade(Produto produto, Integer quantidade, Long idEmpreendimento) {

		// Empreendimento empreendimentoDoUsuario =
		// SessionUsuario.getInstance().getUsuario().getEmpreendimento();
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
