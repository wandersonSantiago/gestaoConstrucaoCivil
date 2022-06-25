package br.com.app.commons.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.app.commons.domain.model.CodigoGerado;

public interface CodigoGeradoRepository extends JpaRepository<CodigoGerado, Long> {

	@Query("SELECT CASE WHEN COUNT(codigo) > 0 THEN true ELSE false END FROM CodigoGerado c WHERE c.codigo = :codigo")
	boolean existeCodigo(@Param("codigo") String codigo);
}
