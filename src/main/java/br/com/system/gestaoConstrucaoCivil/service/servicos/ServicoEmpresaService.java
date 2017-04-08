package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEmpresa;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.ServicoEmpresaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ServicoEmpresaService {


	@Autowired
	private ServicoEmpresaRepository servicoRepository;
	
	public Page<ServicoEmpresa> buscarTodos(PageRequest pages) {
 		return servicoRepository.findAll(pages);
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(ServicoEmpresa servico){
		servicoRepository.save(servico);
	}

	public Iterable<ServicoEmpresa> lista() {
		return servicoRepository.findAll();
	}

	public Iterable<ServicoEmpresa> buscarServicosDaPrestadora(Long id) {		
		return servicoRepository.findByPrestadoraServico_id(id);
	}

	public ServicoEmpresa buscarPorId(Long id) {
		return servicoRepository.findOne(id);
	}
	@Transactional(readOnly = false)
	public void salvarPagamento(ServicoEmpresa servico) {
		servico.setDataFechamento(new Date());
		servico.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		servico.setValorTotalPago(servico.getValorAdicional() + servico.getValorPacoteServico() - servico.getValorDesconto());
		servicoRepository.save(servico);		
	}

	public Iterable<ServicoEmpresa> buscarServicosPagamentoLiberadoDaPrestadora(Long id) {
		return servicoRepository.findByPrestadoraServico_idAndDataFechamentoNotNullAndDataPagamentoNull(id);
	}

		
	@Transactional(readOnly = false)
	public List<ServicoEmpresa> efetuarPagamento(Long id) {
		List<ServicoEmpresa> servicos =  (List<ServicoEmpresa>) servicoRepository.findByPrestadoraServico_idAndDataFechamentoNotNullAndDataPagamentoNull(id);
		for(int i = 0; i < servicos.size(); i ++){
			servicos.get(i).setDataPagamento(new Date());
			servicoRepository.save(servicos.get(i));
		}
		return null;
	}
}
