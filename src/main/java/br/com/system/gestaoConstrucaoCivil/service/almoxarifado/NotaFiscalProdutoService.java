package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscalProduto;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.NotaFiscalProdutoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class NotaFiscalProdutoService {

	@Autowired
	private NotaFiscalProdutoRepository notaFiscalProdutoRepository;
    @Autowired
    private EstoqueEmpreendimentoService estoque;
    
    public List<NotaFiscalProduto> buscarTodos() {

		return notaFiscalProdutoRepository.findAll();
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(NotaFiscalProduto notaFiscalProduto) {
		
		adicionarNotaProdutoItens(notaFiscalProduto);
		notaFiscalProdutoRepository.save(notaFiscalProduto);
		estoque.entradaEstoque(notaFiscalProduto);
		
	}

	public NotaFiscalProduto buscarPorId(Long id) {

		return notaFiscalProdutoRepository.findOne(id);
	}
	
	private void adicionarNotaProdutoItens(NotaFiscalProduto nota)
	{
		for(int i = 0 ; i < nota.getItens().size() ; i++)
		{
			nota.getItens().get(i).setNotaFiscalProduto(nota);
		}
	}
}
