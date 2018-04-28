package br.com.app.repository.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.almoxarifado.NotaFiscal;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal,Long> {

}
