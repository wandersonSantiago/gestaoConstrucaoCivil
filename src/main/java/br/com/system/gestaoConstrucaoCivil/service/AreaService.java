package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Area;
import br.com.system.gestaoConstrucaoCivil.repository.AreaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class AreaService {

	@Autowired
	private AreaRepository areaRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Area area)
	{
		areaRepository.save(area);
	}
	
    public List<Area> buscarTodos(){
		
		return areaRepository.findAll();
	}
    public Area buscarPorId(Long id)
    {
    	return areaRepository.findOne(id);
    }
}
