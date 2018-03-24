package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscal;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.NotaFiscalRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class NotaFiscalService {

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	public Collection<NotaFiscal> buscarTodos() {
		
		return  notaFiscalRepository.findAll();
		
	}

	public NotaFiscal buscarPorId(Long id) {
		
		return notaFiscalRepository.findOne(id);
	}

	public void salvarOuEditar(NotaFiscal notaFiscal) {
		
		notaFiscalRepository.save(notaFiscal);
		
	}
	
}
