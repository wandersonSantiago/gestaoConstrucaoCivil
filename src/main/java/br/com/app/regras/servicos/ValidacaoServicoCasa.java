package br.com.app.regras.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.app.entity.servicos.ServicoCasa;
import br.com.app.repository.servicos.ServicoCasaRepository;

public class ValidacaoServicoCasa implements ValidacaoServico<ServicoCasa>{
	
	@Autowired
	private ServicoCasaRepository servicoCasaRepository;
	
	@Override
	public void verificarExistePacoteParaEmpresa(ServicoCasa servico) {
		
		List<ServicoCasa> servicoCasa = servicoCasaRepository.findAll();

		for (ServicoCasa s : servicoCasa) {

			if (servico.getAndar().equals(s.getAndar()) && servico.getNumero().equals(s.getNumero()) && servico.getPacoteServico().equals(s.getPacoteServico())) {
				throw new ServicoException("Este serviço ja esta cadastrado neste local");

			}

		}
	}

}
