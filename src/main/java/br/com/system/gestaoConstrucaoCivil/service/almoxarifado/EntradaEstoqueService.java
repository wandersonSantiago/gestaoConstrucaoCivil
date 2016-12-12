package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Produto;
import br.com.system.gestaoConstrucaoCivil.pojo.EntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.EstoqueEmpreendimentoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EntradaEstoqueService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;
	
	
	@Transactional(readOnly = false)
    public void entradaEstoque(EntradaOuBaixa entradaOutBaixa)
    {
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		
		
		 if (estoqueRepository.existeProduto(entradaOutBaixa.getProduto().getId(),empreendimentoDoUsuario.getId())) {
				
				adicionarQuantidade(entradaOutBaixa.getProduto(),entradaOutBaixa.getQuantidade());
			} else {
				estoqueRepository.save(criarNovoEstoque(entradaOutBaixa.getProduto(), entradaOutBaixa.getQuantidade()));
				
			}
           
    }
	@Transactional(readOnly = false)
	private void adicionarQuantidade(Produto produto, Integer quantidade) {
		
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(empreendimentoDoUsuario.getId(), produto.getId());
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
