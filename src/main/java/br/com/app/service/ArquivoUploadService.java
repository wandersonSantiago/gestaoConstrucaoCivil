package br.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.ArquivoUpload;
import br.com.app.repository.ArquivoUploadRepository;

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
