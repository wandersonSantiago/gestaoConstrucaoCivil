package br.com.system.gestaoConstrucaoCivil.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.repository.ItemNotaFiscalRepository;

@Service
@Transactional
public class ItemNotaFiscalService {

	@Autowired
	private ItemNotaFiscalRepository itemNotaFiscalRepository;
	
}
