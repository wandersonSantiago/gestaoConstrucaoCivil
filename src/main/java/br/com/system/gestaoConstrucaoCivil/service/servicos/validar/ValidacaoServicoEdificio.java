package br.com.system.gestaoConstrucaoCivil.service.servicos.validar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEdificio;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.ServicoEdificioRepository;

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
				throw new ServicoException("Pacote já vinculado a prestadora de serviço");

			}

		}
	}

}
