package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "arquivo_upload")
public class ArquivoUpload extends AbstractPersistable<Long>{

	private Long proprietarioId ;
	private byte[] file;
	
	public Long getProprietarioId() {
		return proprietarioId;
	}
	public void setProprietarioId(Long proprietarioId) {
		this.proprietarioId = proprietarioId;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	
	
}
