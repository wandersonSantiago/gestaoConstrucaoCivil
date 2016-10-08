package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.ItemNotaFiscalRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ItemNotaFiscalService {

	@Autowired
	private ItemNotaFiscalRepository itemNotaFiscalRepository;

}
