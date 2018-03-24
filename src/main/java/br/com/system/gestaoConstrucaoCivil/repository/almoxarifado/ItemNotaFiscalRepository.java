package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscalItem;



public interface ItemNotaFiscalRepository extends JpaRepository<NotaFiscalItem,Long>{

	
}
