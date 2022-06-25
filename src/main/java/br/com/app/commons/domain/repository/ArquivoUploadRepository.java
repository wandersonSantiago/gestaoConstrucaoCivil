package br.com.app.commons.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.commons.domain.model.ArquivoUpload;

public interface ArquivoUploadRepository extends JpaRepository<ArquivoUpload,Long>{

}
