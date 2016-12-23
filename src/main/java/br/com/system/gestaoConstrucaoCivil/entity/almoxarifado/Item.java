package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.interfaces.IItem;


@Entity
@SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", initialValue = 1, allocationSize = 1)
@Table(name = "item_id_seq")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item implements Serializable,IItem{

	@JsonView(View.Summary.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_id_seq")
	private Long id;
    
	@JsonView(View.Summary.class)
	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;
	
	@JsonView(View.Summary.class)
	@Column(nullable = false)
	private Integer quantidade;
	
	@JsonView(View.Summary.class)
	@Column(nullable = false)
	private Double valorUnitario;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
