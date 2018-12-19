package br.com.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.app.entity.servicos.OcorrenciaServico;
import lombok.Data;

@Entity
@Table(name = "imagens", schema = "communs")
@SequenceGenerator(name = "imagens_id_seq", sequenceName = "imagens_id_seq",allocationSize = 1, schema = "communs")
@Data
public class Imagens implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagens_id_seq")
	private Long id;

	private String descricao;
	
	@Column(name="imagem_base_64", columnDefinition="text")
	private String imagemBase64;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ocorrenciaServico")
	private OcorrenciaServico ocorrencia;

	public String getImagemBase64Path() {
		return imagemBase64 = "data:image/jpeg;base64," + imagemBase64;
	}
	
}
