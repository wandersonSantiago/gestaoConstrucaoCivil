package br.com.app.util.gerador.codigo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.repository.servicos.PacoteServicoRepository;

@Service
public class GereCodigoPacoteServico extends GeraCodigo {

	@Autowired
	PacoteServicoRepository pacoteRepository;
	
	public GereCodigoPacoteServico() {
		
		super(10000,99999);
		
	}
	public Integer gerarNumeroPacote()
	{
		Integer numero = gerarNumero();
		return numero;
	}
}
