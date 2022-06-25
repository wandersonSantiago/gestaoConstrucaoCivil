package br.com.app.financeiro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.financeiro.domain.model.NotaFiscalItem;



public interface ItemNotaFiscalRepository extends JpaRepository<NotaFiscalItem,Long>{

	
}
