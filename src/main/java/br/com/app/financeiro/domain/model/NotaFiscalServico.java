package br.com.app.financeiro.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "nota_fiscal_servico_id_seq", sequenceName = "nota_fiscal_servico_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "nota_fiscal_servico", schema = "almoxarifado")
public class NotaFiscalServico {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_fiscal_servico_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_nota_fiscal", nullable = true)
	private NotaFiscal notaFiscal;

}
