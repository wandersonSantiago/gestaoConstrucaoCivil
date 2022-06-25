package br.com.app.commons.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "estrutura", schema = "communs")
@SequenceGenerator(name = "estrutura_id_seq", sequenceName = "estrutura_id_seq",allocationSize = 1, schema = "communs")
@Data
public class Estrutura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estrutura_id_seq")
	private Long id;

	private String descricao;

	@ManyToOne
	private Estrutura raiz;

	private Long idEmpreendimento;

	public boolean isRaiz() {
		return raiz == null;
		 
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();		
	}
}
