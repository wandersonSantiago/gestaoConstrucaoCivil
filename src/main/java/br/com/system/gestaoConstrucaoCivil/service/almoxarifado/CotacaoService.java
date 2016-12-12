package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Cotacao;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusCotacao;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.CotacaoRepository;

@Service
@Transactional(readOnly=true, propagation = Propagation.REQUIRED)
public class CotacaoService {
	
	@Autowired
	private CotacaoRepository cotacaoRepository;
	@Autowired
	private CotacaoEmpresaService cotacaoEmpresa;
	
	@Transactional(readOnly = false)
	public void salvaAltera(Cotacao cotacao){
		cotacao.setDataCriacao(new Date());
		cotacao.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		adicionarCotacaoNoItem(cotacao);
		cotacao.setStatusCotacao(StatusCotacao.ABERTO);
		cotacaoRepository.save(cotacao);
	}
	private void adicionarCotacaoNoItem(Cotacao cotacao) {
		for (int i = 0; i < cotacao.getItens().size(); i++) {
		
			cotacao.getItens().get(i).setContacao(cotacao);
		}
		
	
	}
	
	public Collection<Cotacao> buscarTodos(){
		return cotacaoRepository.findAll();
	}
	
	public Cotacao buscaPorId(Long id){
		return cotacaoRepository.findOne(id);
	}
	public void fecharCotacao(Long idCotacao)
	{
		Cotacao cotacao = cotacaoRepository.findOne(idCotacao);
		cotacao.setStatusCotacao(StatusCotacao.FECHADO);
		cotacaoEmpresa.verificarItensGanhadores(cotacao);
	}

}
