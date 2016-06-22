package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "item_nota_fiscal")
public class ItemNotaFiscal extends AbstractPersistable<Long>{

	
}
