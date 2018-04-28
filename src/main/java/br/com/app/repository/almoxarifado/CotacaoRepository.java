package br.com.app.repository.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.almoxarifado.Cotacao;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long>{

}
