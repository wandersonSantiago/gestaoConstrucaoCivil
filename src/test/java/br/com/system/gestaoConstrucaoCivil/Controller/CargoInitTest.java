package br.com.system.gestaoConstrucaoCivil.Controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.GestaoConstrucaoCivilApplication;
import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.repository.CargoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GestaoConstrucaoCivilApplication.class)
public class CargoInitTest {

	@Autowired
	private CargoRepository cargoRepository;
	
	private static final String DEFAULT_DESCRICAO = "CARGO JUNIT";
	private static final String UPDATED_DESCRICAO = "CARGO EDITADO JUNIT";
	
	private Cargo cargo;
	
	public Cargo criarEntity()
	{
	    Cargo cargo = new Cargo();
	    cargo.setDescricao(DEFAULT_DESCRICAO);
	    return cargo;
	    
	}
	@Before
	public void initTest() {
		
		this.cargo = criarEntity();
	}
	
	@Test
	@Transactional 
	public void salvar()
	{
		cargoRepository.save(cargo);
		
		List<Cargo> cargos = cargoRepository.findAll();
		
		Cargo testCargo = cargos.get(cargos.size() - 1);
		
		 assertThat(testCargo.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
		 
	}
	
	@Test
	@Transactional 
	public void editar()
	{
		cargoRepository.save(cargo);
		
		Cargo cargoEditado = cargoRepository.findOne(cargo.getId());
		cargoEditado.setDescricao(UPDATED_DESCRICAO);
		cargoRepository.save(cargoEditado);
		
		List<Cargo> cargos = cargoRepository.findAll();
		Cargo testCargo = cargos.get(cargos.size() - 1);
		assertThat(testCargo.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
		
	}
}
