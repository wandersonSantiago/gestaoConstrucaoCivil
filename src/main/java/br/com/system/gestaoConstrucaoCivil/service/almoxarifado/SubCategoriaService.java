package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.SubCategoria;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.SubCategoriaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class SubCategoriaService {


	@Autowired
	private SubCategoriaRepository subCategoriaRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(SubCategoria subCategoria)
	{
		subCategoriaRepository.save(subCategoria);
	}
}
