package br.com.app.financeiro.domain.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.financeiro.domain.model.NotaFiscal;
import br.com.app.financeiro.domain.repository.NotaFiscalRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class NotaFiscalService {

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	public Collection<NotaFiscal> buscarTodos() {
		
		return  notaFiscalRepository.findAll();
		
	}

	public Optional<NotaFiscal> buscarPorId(Long id) {
		
		return notaFiscalRepository.findById(id);
	}

	public void salvarOuEditar(NotaFiscal notaFiscal) {
		
		notaFiscalRepository.save(notaFiscal);
		
	}
	
}
