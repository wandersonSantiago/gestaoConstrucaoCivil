package br.com.system.gestaoConstrucaoCivil.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.repository.DadoEmpresaRepository;

@Service
@Transactional
public class DadoEmpresaService {

	@Autowired
	private DadoEmpresaRepository dadoEmpresaRepository;
	
}
