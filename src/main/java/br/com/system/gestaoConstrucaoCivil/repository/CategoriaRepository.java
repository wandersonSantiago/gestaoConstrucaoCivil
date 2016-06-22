package br.com.system.gestaoConstrucaoCivil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.system.gestaoConstrucaoCivil.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
