package br.com.system.gestaoConstrucaoCivil.criarObjecto;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.system.gestaoConstrucaoCivil.criarObjecto.util.GerarInformacao;
import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.entity.Endereco;
import br.com.system.gestaoConstrucaoCivil.entity.Funcionario;
import br.com.system.gestaoConstrucaoCivil.service.CargoService;
import br.com.system.gestaoConstrucaoCivil.service.EmpreendimentoService;


public class CriaFuncionario {

	
	private CargoService cargoService = new CargoService();
	
	private EmpreendimentoService empreendimentoService = new EmpreendimentoService();
	
	
	private Funcionario funcionario;
	public CriaFuncionario() {
		
		funcionario = new Funcionario();
	    funcionario.setRg("515779845");
		funcionario.setIdade(28);
	    funcionario.setAtivo(true);
	    funcionario.setNomeCompleto("Jose da Silva");
	    funcionario.setCpf("11111111");
	   // funcionario.setDataAdmissao(LocalDate.now());
	    funcionario.setEndereco(new CriaEndereco().getEndereco());
	    funcionario.setCarteiraTrabalho(21231);
	    funcionario.setCargo(cargoService.buscarPorId(9999L));
	    
	    funcionario.setEmpreendimento(empreendimentoService.buscarPorId(9999L));
	    funcionario.setTelefoneFixo("1938740076");
	    funcionario.setTelefoneCelular("199874454");
	  /*  funcionario.setSexo("M");*/
	    funcionario.setCrea(54887454);
	    
	    LocalDate data = LocalDate.of(1, 12, 1987);
		//funcionario.setDataNascimento(data);
	}
    
	public Funcionario getFuncionario()
    {
    	System.out.println(funcionario.toString());
		return funcionario;
    }
	
}
