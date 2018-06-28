package br.com.app.entity.almoxarifado;

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

import br.com.app.entity.almoxarifado.interfaces.IItem;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", allocationSize = 1, schema = "almoxarifado")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item implements Serializable, IItem {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private Double valorUnitario;

	public double getValorTotal() {
		return quantidade * valorUnitario;
	}
}
