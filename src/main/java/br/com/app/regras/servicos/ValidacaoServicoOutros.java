package br.com.app.regras.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.app.entity.servicos.ServicoOutros;
import br.com.app.repository.servicos.ServicoOutrosRepository;

public class ValidacaoServicoOutros implements ValidacaoServico<ServicoOutros>{
	
	@Autowired
	private ServicoOutrosRepository servicoOutrosRepository;
	
	@Override
	public void verificarExistePacoteParaEmpresa(ServicoOutros servico) {
		
		List<ServicoOutros> servicoOutros = servicoOutrosRepository.findAll();

		for (ServicoOutros s : servicoOutros) {

			if (servico.getId().equals(s.getId()) && servico.getPacoteServico().equals(s.getPacoteServico())) {
				throw new ServicoException("Este serviço ja esta cadastrado neste local");

			}

		}
	}

}
