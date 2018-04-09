package br.com.app.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.app.entity.ArquivoUpload;
import br.com.app.service.ArquivoUploadService;

@RestController
@RequestMapping("/rest/file")
public class FileUploadController {

	@Autowired 
	private ArquivoUploadService service;
	
	@PostMapping("/upload")
	public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file,
            Long proprietarioId,RedirectAttributes redirectAttributes) 
	{
		
		if(file.isEmpty())
		{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	
		ArquivoUpload arquivo = new ArquivoUpload();
		try
		{
		arquivo.setFile(file.getBytes());
		arquivo.setProprietarioId(proprietarioId);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		service.salvar(arquivo);
		return new ResponseEntity(HttpStatus.OK);
	}
}
