package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.TransferenciaRepository;



@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TransferenciaService {
	
	@Autowired
	private TransferenciaRepository transferenciaRepository;
	@Autowired
	private BaixaEstoqueService baixarEstoque;
	@Autowired 
	private EntradaEstoqueService entradaEstoque;
	
	
	@Transactional(readOnly = false)
	public void salvarAlterar(Transferencia transferencia) {

        transferencia.novaTransferencia();
        baixarEstoque.baixar(transferencia);
		transferenciaRepository.save(transferencia);
	}
	@Transactional(readOnly = false)
	public void aceitarTransferencia(Long numeroNota)
	{
		
		Transferencia transferencia =  transferenciaRepository.buscarTransferenciaPorNumeroNota(numeroNota);
		transferencia.aceitarTransferencia();
		entradaEstoque.entradaEstoque(transferencia);
	    transferenciaRepository.save(transferencia);
	}
	@Transactional(readOnly = false)
	public void rejeitarTransferencia(Long numeroNota)
	{
		Transferencia transferencia =  transferenciaRepository.buscarTransferenciaPorNumeroNota(numeroNota);
		transferencia.rejeitarTransferencia();
		entradaEstoque.entradaEstoque(transferencia);
		transferenciaRepository.save(transferencia);
	}
	
	public Collection<Transferencia> buscarTodos(){
		return transferenciaRepository.findAll();
	}

	public Transferencia buscaPorId(Long id){
		return transferenciaRepository.findOne(id);
	}
	public Collection<Transferencia>  buscarTransferenciaRecebida() {
		
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		return transferenciaRepository.buscarTransferenciaRecebidas(idEmpreendimento);
	}
	public Collection<Transferencia>  buscarTransferenciaEnviada() {
		
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		return transferenciaRepository.buscarTransferenciaEnviada(idEmpreendimento);
	}
	
}
