package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends AbstractPersistable<Long>{

	
}
