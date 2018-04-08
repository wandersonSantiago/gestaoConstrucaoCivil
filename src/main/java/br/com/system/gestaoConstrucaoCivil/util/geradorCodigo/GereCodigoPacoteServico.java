package br.com.system.gestaoConstrucaoCivil.util.geradorCodigo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.repository.servicos.PacoteServicoRepository;

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
