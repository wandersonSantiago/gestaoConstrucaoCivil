package br.com.app.service.servicos;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.servicos.ServicoEmpresa;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.servicos.ServicoEmpresaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ServicoEmpresaService {

	@Autowired
	private ServicoEmpresaRepository servicoRepository;

	public Page<ServicoEmpresa> buscarTodos(PageRequest pages) {
		return servicoRepository.findAll(pages);
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(ServicoEmpresa servico) {
		servicoRepository.save(servico);
	}

	public Collection<ServicoEmpresa> lista() {
		return servicoRepository.findAll();
	}

	public Collection<ServicoEmpresa> buscarServicosDaPrestadora(Long id) {
		return servicoRepository.findByPrestadoraServico_id(id);
	}

	public Optional<ServicoEmpresa> buscarPorId(Long id) {
		return servicoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public void salvarPagamento(ServicoEmpresa servico) {
		servico.setDataFechamento(new Date());
		servico.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		servico.setValorTotalPago(
				servico.getValorAdicional() + servico.getValorPacoteServico() - servico.getValorDesconto());
		servicoRepository.save(servico);
	}

	public Collection<ServicoEmpresa> buscarServicosPagamentoLiberadoDaPrestadora(Long id) {
		return servicoRepository.findByPrestadoraServico_idAndDataFechamentoNotNullAndDataPagamentoNull(id);
	}

	@Transactional(readOnly = false)
	public void efetuarPagamento(Long id) {
		List<ServicoEmpresa> servicos = (List<ServicoEmpresa>) servicoRepository
				.findByPrestadoraServico_idAndDataFechamentoNotNullAndDataPagamentoNull(id);
		for (int i = 0; i < servicos.size(); i++) {
			servicos.get(i).setDataPagamento(new Date());
			servicoRepository.save(servicos.get(i));
		}

	}
}
