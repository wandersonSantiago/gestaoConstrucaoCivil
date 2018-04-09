package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.ArquivoUpload;

public interface ArquivoUploadRepository extends JpaRepository<ArquivoUpload,Long>{

}
