package br.com.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@SequenceGenerator(name = "arquivo_produto_id_seq", sequenceName = "arquivo_produto_id_seq",allocationSize = 1,schema="communs")
@Table(name = "arquivo_upload" , schema = "communs")
public class ArquivoUpload implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arquivo_produto_id_seq")
	private Long id;
	
	private Long proprietarioId ;
	private byte[] file;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
