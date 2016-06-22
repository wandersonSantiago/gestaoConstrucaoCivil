package br.com.system.gestaoConstrucaoCivil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.gestaoConstrucaoCivil.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

}
