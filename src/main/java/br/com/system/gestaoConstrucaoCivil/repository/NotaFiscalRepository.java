package br.com.system.gestaoConstrucaoCivil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.entity.NotaFiscal;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal,Long> {

}
