package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasa;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoCasaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoCasaService {

	@Autowired
	private RequisicaoCasaRepository requisicaoCasaRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoque;

	@Transactional(readOnly = false)
	public void salvarOuEditar(List<RequisicaoCasa> requisicaoCasa) {
		for (RequisicaoCasa requisicao : requisicaoCasa) {
			requisicao.setDataSaida(LocalDate.now());
			estoque.baixarEstoque(requisicao);
		}
		requisicaoCasaRepository.save(requisicaoCasa);
	}
	public List<RequisicaoCasa> buscarTodos() {
		return requisicaoCasaRepository.findAll();
	}
	public RequisicaoCasa buscarPorId(Long id) {
		return requisicaoCasaRepository.findOne(id);
	}

}
