package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transferencia_item", schema="almoxarifado")
public class TransferenciaItem  extends Item {
	
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_transferencia")
	private Transferencia transferencia;

	public Transferencia getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}
	
}
