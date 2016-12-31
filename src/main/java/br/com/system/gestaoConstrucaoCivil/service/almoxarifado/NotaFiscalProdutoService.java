package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscalItem;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscalProduto;
import br.com.system.gestaoConstrucaoCivil.pojo.InformacaoEntradaProduto;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.NotaFiscalProdutoRepository;
import br.com.system.gestaoConstrucaoCivil.service.EmpreendimentoService;

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
    private EmpreendimentoService empreendimentoService;
    
    
	public List<NotaFiscalProduto> buscarTodos() {

		return notaFiscalProdutoRepository.findAll();
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(NotaFiscalProduto notaFiscalProduto) {

		notaFiscalProduto.novaNotaProduto();
		notaFiscalProdutoRepository.save(notaFiscalProduto);
		empreendimentoService.adcionarValorGasto(notaFiscalProduto.getNotaFiscal().getValorTotal());
	    entradaEstoque.entradaEstoque(notaFiscalProduto);
      
     }

	public NotaFiscalProduto buscarPorId(Long id) {

		return notaFiscalProdutoRepository.findOne(id);
	}
	public InformacaoEntradaProduto getInformacaoProduto(Long idProduto) {

		return gerarInformacao(idProduto);
	}

	private InformacaoEntradaProduto gerarInformacao(Long idProduto) {

		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		List<NotaFiscalProduto> notas = notaFiscalProdutoRepository.buscarNotaPorEmpreendimento(idEmpreendimento);
		Double valorTotal = 0.0;
		Integer quantidadeTotal = 0;
		for (NotaFiscalProduto notaFiscal : notas) {

			for (NotaFiscalItem item : notaFiscal.getItens()) {
				
				if (item.getProduto().getId().equals(idProduto)) {
					
					valorTotal += item.getValorTotal();
					quantidadeTotal += item.getQuantidade();
				}
			}
		}
		return new InformacaoEntradaProduto(valorTotal, quantidadeTotal);
	}
	@Transactional(readOnly = false)
	public void cancelarEntrada(Long numeroEntrada)
	{
		NotaFiscalProduto nota = notaFiscalProdutoRepository.buscarPorNumero(numeroEntrada);
		baixarEstoque.baixar(nota);
		empreendimentoService.removerValorGasto(nota.getNotaFiscal().getValorTotal());
		nota.getNotaFiscal().cancelarNota();
		notaFiscalProdutoRepository.save(nota);
	}
}
