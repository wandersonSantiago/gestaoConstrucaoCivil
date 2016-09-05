package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.NotaFiscalProtudo;
import br.com.system.gestaoConstrucaoCivil.repository.NotaFiscalProdutoRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class NotaFiscalProdutoService {

	@Autowired
	private NotaFiscalProdutoRepository notaFiscalProdutoRepository ;
	
	public List<NotaFiscalProtudo> buscarTodos() {

		return notaFiscalProdutoRepository.findAll();
	}
	@Transactional(readOnly = false)
	public void salvarOuEditar(NotaFiscalProtudo notaFiscalProtudo)
	{
		notaFiscalProdutoRepository.save(notaFiscalProtudo);
	}
	public NotaFiscalProtudo buscarPorId(Long id) {
		
		return notaFiscalProdutoRepository.findOne(id);
	}
}
