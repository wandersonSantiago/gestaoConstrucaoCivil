package br.com.system.gestaoConstrucaoCivil.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.repository.PacoteServicoRepository;

@Service
@Transactional
public class PacoteServicoService {

	@Autowired
	private PacoteServicoRepository pacoteServicoRepository;
	
}
