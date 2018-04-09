package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.AreaProduto;

public interface AreaRepository extends JpaRepository<AreaProduto,Long>{

}
