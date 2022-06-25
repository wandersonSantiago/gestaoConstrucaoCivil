package br.com.app.commons.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.commons.domain.model.ArquivoUpload;
import br.com.app.commons.domain.repository.ArquivoUploadRepository;

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
