package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Cotacao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresa;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresaItem;
import br.com.system.gestaoConstrucaoCivil.enuns.CotacaoEmpresaItemStatus;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.CotacaoEmpresaItemRepository;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.CotacaoEmpresaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CotacaoEmpresaService {

	@Autowired
	private CotacaoEmpresaRepository cotacaoEmpresaRepository;
	@Autowired
	private CotacaoEmpresaItemRepository cotaEmpresaItemReposi;


	@Transactional(readOnly = false)
	public void salvarOuEditar(CotacaoEmpresa cotacaoEmpresa) {

		for (CotacaoEmpresaItem item : cotacaoEmpresa.getItens()) {
			item.setId(null);
			item.setStatus(CotacaoEmpresaItemStatus.PENDENTE);
			item.setCotacaoEmpresa(cotacaoEmpresa);
		}
		cotacaoEmpresaRepository.save(cotacaoEmpresa);
	}

	public List<CotacaoEmpresa> buscarTodos() {

		return cotacaoEmpresaRepository.findAll();
	}

	public CotacaoEmpresa buscarPorId(Long id) {

		return cotacaoEmpresaRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void verificarGanhadores(Cotacao cotacao) {
		List<CotacaoEmpresaItem> itens = cotaEmpresaItemReposi.buscarItensPorIdCotacao(cotacao.getId());

		Collections.sort(itens, new Comparator<CotacaoEmpresaItem>() {

			@Override
			public int compare(CotacaoEmpresaItem item1, CotacaoEmpresaItem item2) {

			    if(item1.getItem().getId() == item2.getItem().getId())
			    {
				if(item1.getValorUnitario() > item2.getValorUnitario())
				{
					item1.setStatus(CotacaoEmpresaItemStatus.PERDEU);
					item2.setStatus(CotacaoEmpresaItemStatus.GANHOU);
					return 1;
				}else
				{
					item2.setStatus(CotacaoEmpresaItemStatus.PERDEU);
					item1.setStatus(CotacaoEmpresaItemStatus.GANHOU);
					return -1;
				}
			    }
			    return 0;
				
			}
		});
		cotaEmpresaItemReposi.save(itens);
	}
}
