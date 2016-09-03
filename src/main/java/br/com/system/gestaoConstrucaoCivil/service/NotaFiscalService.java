package br.com.system.gestaoConstrucaoCivil.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.NotaFiscal;
import br.com.system.gestaoConstrucaoCivil.repository.NotaFiscalRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class NotaFiscalService {

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	public Iterable<NotaFiscal> buscarTodos() {
		
		return  notaFiscalRepository.findAll();
		
	}

	public NotaFiscal buscarFornecedorPorId(Long id) {
		
		return notaFiscalRepository.findOne(id);
	}

	public void salvarOuEditar(NotaFiscal notaFiscal) {
		
		notaFiscalRepository.save(notaFiscal);
		
	}
	
}
