package br.com.app.regras.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.entity.servicos.ServicoEdificio;
import br.com.app.repository.servicos.ServicoEdificioRepository;

@Service
public class ValidacaoServicoEdificio implements ValidacaoServico<ServicoEdificio> {

	@Autowired
	private ServicoEdificioRepository servicoEdificioRepository;
	
	@Override
	public void verificarExistePacoteParaEmpresa(ServicoEdificio servico) {
		
		List<ServicoEdificio> servicosEdificios = servicoEdificioRepository.findAll();

		for (ServicoEdificio s : servicosEdificios) {

			if (servico.getTorre().equals(s.getTorre()) && servico.getApartamento().equals(s.getApartamento())
					&& servico.getPacoteServico().equals(s.getPacoteServico())) {
				throw new ServicoException("Este serviço ja esta cadastrado neste local");

			}

		}
	}

}
