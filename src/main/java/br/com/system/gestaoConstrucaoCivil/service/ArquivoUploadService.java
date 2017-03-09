package br.com.system.gestaoConstrucaoCivil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.ArquivoUpload;
import br.com.system.gestaoConstrucaoCivil.repository.ArquivoUploadRepository;

@Service
@Transactional(readOnly = true)
public class ArquivoUploadService {

	@Autowired
	private ArquivoUploadRepository arquivoUploadRepository;
	
	@Transactional(readOnly = false)
	public void salvar(ArquivoUpload arquivoUpload)
	{
	  arquivoUploadRepository.save(arquivoUpload);	
	}
}
