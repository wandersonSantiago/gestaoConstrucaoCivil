package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.AreaProduto;
import br.com.system.gestaoConstrucaoCivil.repository.AreaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class AreaService {

	@Autowired
	private AreaRepository areaRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(AreaProduto areaProduto)
	{
		areaRepository.save(areaProduto);
	}
	
    public List<AreaProduto> buscarTodos(){
		
		return areaRepository.findAll();
	}
    public AreaProduto buscarPorId(Long id)
    {
    	return areaRepository.findOne(id);
    }
}
