package br.com.app.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.app.entity.ConfiguracaoEmpreendimento;
import br.com.app.entity.Empreendimento;
import br.com.app.entity.Endereco;
import br.com.app.entity.Usuario;
import br.com.app.enuns.StatusEmpreendimento;
import br.com.app.enuns.StatusUsuarioEnum;
import br.com.app.repository.ConfiguracaoEmpreendimentoRepository;
import br.com.app.repository.EmpreendimentoRepository;
import br.com.app.repository.UsuarioRepository;


@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private EmpreendimentoRepository empreendimentoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ConfiguracaoEmpreendimentoRepository configRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Endereco endereco = new Endereco();
		endereco.setId(null);
		endereco.setBairro("Jardins das Palmeiras");
		endereco.setCep("13174371");
		endereco.setComplemento("Cidade velha");
		endereco.setLocalidade("Sumaré");
		endereco.setLogradouro("Rua Antonio Anastasia");
		endereco.setNumero("789456");
		endereco.setUf("SP");
		
		Endereco ende = new Endereco();
		ende.setId(null);
		ende.setBairro("Jardins das Palmeiras");
		ende.setCep("13174371");
		ende.setComplemento("Cidade velha");
		ende.setLocalidade("Sumaré");
		ende.setLogradouro("Rua Antonio Anastasia");
		ende.setNumero("789456");
		ende.setUf("SP");
		
		Empreendimento empMatriz = empreendimentoRepository.save(new Empreendimento(null,endereco,"KLP Construções", "199895045", 5000000.0, 0.0, 0.0 , new Date(), new Date(), StatusEmpreendimento.EM_ANDAMENTO, null));
		
		Empreendimento emp = empreendimentoRepository.save(new Empreendimento(null,ende,"Obra Sara",  "199895045", 5000000.0, 0.0,0.0,new Date(),new Date(), StatusEmpreendimento.EM_ANDAMENTO,empMatriz)); 
		
		Usuario userPrincipal = new Usuario(null, empMatriz, "root", "root", "root@root.com", pe.encode("root"), true, null, StatusUsuarioEnum.INDISPONIVEL, new Date());
		Usuario user= new Usuario(null, emp, "Wanderson", "Wanderson", "wandersonsantiago@gmail.com", pe.encode("123456"), true, null, StatusUsuarioEnum.INDISPONIVEL, new Date());
		
		ConfiguracaoEmpreendimento config = new ConfiguracaoEmpreendimento();
		config.setDataBaseFinanceiro(7);
		config.setEmpreendimento(empMatriz);
		configRepository.save(config);
		
		ConfiguracaoEmpreendimento config2 = new ConfiguracaoEmpreendimento();
		config2.setDataBaseFinanceiro(1);
		config2.setEmpreendimento(emp);
		configRepository.save(config2);
		
		usuarioRepository.save(userPrincipal);
		usuarioRepository.save(user);
		
	
	}
}
