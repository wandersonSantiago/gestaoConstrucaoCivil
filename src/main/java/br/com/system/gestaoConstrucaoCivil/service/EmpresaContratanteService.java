package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;
import br.com.system.gestaoConstrucaoCivil.repository.EmpresaContratanteRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class EmpresaContratanteService {

	@Autowired
	private EmpresaContratanteRepository empresaContratanteRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(EmpresaContratante empresaContratante)
	{
		empresaContratanteRepository.save(empresaContratante);
	}
	
	public EmpresaContratante buscarPorId(Long id)
	{
		return empresaContratanteRepository.findOne(id);
	}
	
	public List<EmpresaContratante> buscarTodos(){
		
		return empresaContratanteRepository.findAll();
	}
	public List<EmpresaContratante> buscarPorDescricaoDoCampo(String descricao)
	{
	
		List<EmpresaContratante> empresas = empresaContratanteRepository.buscarPorDescricao(descricao.trim());
		System.out.println("TAMANHO:" + empresas.size());
		
		for(EmpresaContratante e : empresas){
			
			System.out.println(e.getDadoEmpresa().getRazaoSocial());
		}
		return empresas;
		
	}
	public Page<EmpresaContratante> buscarTodos(PageRequest pageRequest) {
		
		//buscarPorDescricaoDoCampo(" Sophia e Gabriel Eletrônica Ltda                  ");
		buscarPorDescricaoDoCampo("Sophia");
		
		
		return empresaContratanteRepository.findAll(pageRequest);
	}
	
}
