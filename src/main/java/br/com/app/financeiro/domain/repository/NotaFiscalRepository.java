package br.com.app.financeiro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.financeiro.domain.model.NotaFiscal;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal,Long> {

}
