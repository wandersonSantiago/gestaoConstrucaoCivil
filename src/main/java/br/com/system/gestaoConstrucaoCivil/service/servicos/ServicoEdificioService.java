package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEdificio;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.ServicoEdificioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ServicoEdificioService {

	@Autowired
	private ServicoEdificioRepository servicoEdificioRepository;

	public List<ServicoEdificio> buscarTodos() {

		return servicoEdificioRepository.findAll();
	}

	private void verificarExistePacoteParaEmpresa(ServicoEdificio servico) {

		List<ServicoEdificio> servicosEdificios = servicoEdificioRepository.findAll();

		for (ServicoEdificio s : servicosEdificios) {

			if (servico.getTorre().equals(s.getTorre()) && servico.getApartamento().equals(s.getApartamento())
					&& servico.getPacoteServico().equals(s.getPacoteServico())) {
				throw new ServicoEdificioException("Pacote já vinculado a prestadora de serviço");

			}

		}

	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(ServicoEdificio servico) {
		servico.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		verificarExistePacoteParaEmpresa(servico);
		servicoEdificioRepository.save(servico);

	}
}