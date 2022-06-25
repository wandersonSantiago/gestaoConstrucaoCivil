package br.com.app.estoque.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.estoque.domain.model.TipoProduto;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto , Long >{

}
