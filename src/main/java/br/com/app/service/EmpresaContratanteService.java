package br.com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.EmpresaContratante;
import br.com.app.repository.EmpresaContratanteRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EmpresaContratanteService {

	@Autowired
	private EmpresaContratanteRepository empresaContratanteRepository;

	@Transactional(readOnly = false)
	public void salvarOuEditar(EmpresaContratante empresaContratante) {
		empresaContratanteRepository.save(empresaContratante);
	}

	public Optional<EmpresaContratante> buscarPorId(Long id) {
		return empresaContratanteRepository.findById(id);
	}

	public List<EmpresaContratante> buscarTodos() {

		return empresaContratanteRepository.findAll();
	}

	public List<EmpresaContratante> buscarPorDescricaoDoCampo(String descricao) {

		List<EmpresaContratante> empresas = empresaContratanteRepository.buscarPorDescricao(descricao.trim());

		for (EmpresaContratante e : empresas) {

			System.out.println(e.getDadoEmpresa().getRazaoSocial());
		}
		return empresas;

	}

	public Page<EmpresaContratante> buscarTodos(PageRequest pageRequest) {

		// buscarPorDescricaoDoCampo(" Sophia e Gabriel Eletrônica Ltda ");
		buscarPorDescricaoDoCampo("Sophia");

		return empresaContratanteRepository.findAll(pageRequest);
	}

}
