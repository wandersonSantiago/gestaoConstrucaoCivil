package br.com.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "estrutura" , schema = "communs")
@Data
public class Estrutura implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	
	private String descricao;
	
	@ManyToOne
	private Estrutura raiz;

	
	
	
	public boolean isRaiz() {
		if(raiz == null) {
			return true;
		}
		return false;
	}
}
