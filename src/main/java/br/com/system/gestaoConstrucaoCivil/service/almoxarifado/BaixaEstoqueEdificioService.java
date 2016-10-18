package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.BaixaEstoqueEdificio;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.BaixaEstoqueEdificioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BaixaEstoqueEdificioService {

	@Autowired
	private BaixaEstoqueEdificioRepository baixaEstoqueEdificioRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoque;

	@Transactional(readOnly = false)
	public void salvarOuEditar(List<BaixaEstoqueEdificio> baixasEstoqueEdificio) {

		
		for (BaixaEstoqueEdificio baixa : baixasEstoqueEdificio) {

			baixa.setDataSaida(LocalDate.now());
			estoque.baixarEstoque(baixa);
		}
		baixaEstoqueEdificioRepository.save(baixasEstoqueEdificio);
		
	}

	public List<BaixaEstoqueEdificio> buscarTodos() {
		return baixaEstoqueEdificioRepository.findAll();
	}

	public BaixaEstoqueEdificio buscarPorId(Long id) {
		return baixaEstoqueEdificioRepository.findOne(id);
	}
}
