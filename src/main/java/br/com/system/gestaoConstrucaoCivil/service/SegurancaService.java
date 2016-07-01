package br.com.system.gestaoConstrucaoCivil.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.repository.SegurancaRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class SegurancaService {


	@Autowired
	private SegurancaRepository segurancaRepository;
	
}
