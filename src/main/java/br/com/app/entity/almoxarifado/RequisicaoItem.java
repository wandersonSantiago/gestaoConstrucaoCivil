package br.com.app.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.app.entity.AreaProduto;

@Entity
@Table(name = "requisicao_item", schema="almoxarifado")
public class RequisicaoItem extends Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
}
