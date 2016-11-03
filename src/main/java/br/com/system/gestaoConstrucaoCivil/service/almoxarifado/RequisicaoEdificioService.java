package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoEdificio;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoEdificioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoEdificioService {

	@Autowired
	private RequisicaoEdificioRepository requisicaoEdificioRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoque;

	@Transactional(readOnly = false)
	public void salvarOuEditar(List<RequisicaoEdificio> requisicaoEdificio) {

		
		for (RequisicaoEdificio requisicao : requisicaoEdificio) {

			requisicao.setDataSaida(LocalDate.now());
			estoque.baixarEstoque(requisicao);
		}
		requisicaoEdificioRepository.save(requisicaoEdificio);
		
	}

	public List<RequisicaoEdificio> buscarTodos() {
		return requisicaoEdificioRepository.findAll();
	}

	public RequisicaoEdificio buscarPorId(Long id) {
		return requisicaoEdificioRepository.findOne(id);
	}
}
