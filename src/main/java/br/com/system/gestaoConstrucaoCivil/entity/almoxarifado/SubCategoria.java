package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.system.gestaoConstrucaoCivil.entity.Categoria;


@Entity
@Table(name = "sub_categoria")
public class SubCategoria extends AbstractPersistable<Long>{

	
	@Column(nullable = false,length = 50)
	private String descricao;
	@OneToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
