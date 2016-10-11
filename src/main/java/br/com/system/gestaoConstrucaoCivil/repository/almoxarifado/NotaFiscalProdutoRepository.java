package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscalProduto;

public interface NotaFiscalProdutoRepository extends JpaRepository<NotaFiscalProduto,Long> {


	@Query("FROM NotaFiscalProduto")
	public List<NotaFiscalProduto> buscarNota();
}
