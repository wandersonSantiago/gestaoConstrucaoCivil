package br.com.app.servico.domain.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.servico.domain.model.ServicoEmpresa;
import br.com.app.servico.domain.repository.ServicoEmpresaRepository;

@Service
public class ValidacaoServico {

	@Autowired
	private ServicoEmpresaRepository servicoRepository;

	public void verificarExistePacoteParaEmpresa(ServicoEmpresa servico) {

		Collection<ServicoEmpresa> servicosEmpresa = servicoRepository.findByEstrutura(servico.getEstrutura());

		for (ServicoEmpresa s : servicosEmpresa) {

			if (servico.getPacoteServico().equals(s.getPacoteServico())) {
				throw new ServicoException("Este serviço já esta cadastrado neste local");
			}

		}
	}

}
