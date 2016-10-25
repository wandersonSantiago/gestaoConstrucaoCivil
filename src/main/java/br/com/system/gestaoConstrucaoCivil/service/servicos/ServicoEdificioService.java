package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.List;

import javax.websocket.Session;

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

	private boolean verificarExistePacoteParaEmpresa(ServicoEdificio servico) {
		List<ServicoEdificio> servicosEdificios = servicoEdificioRepository.findAll();

		for (ServicoEdificio s : servicosEdificios) {

			if (servico.getTorre() == s.getTorre() && servico.getApartamento() == s.getApartamento()
					&& s.getPacoteServico().equals(servico.getPacoteServico()))

			{

				return true;

			}

		}
		return false;
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(ServicoEdificio servico) {
		servico.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		if (verificarExistePacoteParaEmpresa(servico) != true) {
			servicoEdificioRepository.save(servico);
		}
	}
}