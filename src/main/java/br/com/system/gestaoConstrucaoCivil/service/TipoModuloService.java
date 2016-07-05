package br.com.system.gestaoConstrucaoCivil.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.TipoModulo;
import br.com.system.gestaoConstrucaoCivil.repository.TipoModuloRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class TipoModuloService {


	@Autowired
	private TipoModuloRepository tipoModuloRepository;
	
	@Transactional
	public void salvarOuEditar(TipoModulo tipoModulo)
	{
		tipoModuloRepository.save(tipoModulo);
	}
}
