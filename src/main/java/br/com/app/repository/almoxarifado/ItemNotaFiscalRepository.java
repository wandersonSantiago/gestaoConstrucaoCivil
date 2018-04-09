package br.com.app.repository.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.almoxarifado.NotaFiscalItem;



public interface ItemNotaFiscalRepository extends JpaRepository<NotaFiscalItem,Long>{

	
}
