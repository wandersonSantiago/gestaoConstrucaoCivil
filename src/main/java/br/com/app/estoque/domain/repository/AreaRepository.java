package br.com.app.estoque.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.estoque.domain.model.AreaProduto;

public interface AreaRepository extends JpaRepository<AreaProduto,Long>{

}
