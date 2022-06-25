package br.com.app.financeiro.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "aluguel_equipamento_id_seq", sequenceName = "aluguel_equipamento_id_seq", allocationSize = 1, schema = "communs")
@Table(name = "aluguel_equipamento", schema = "communs")
public class AluguelEquipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluguel_equipamento_id_seq")
	private Long id;

	private String nomeEquipamento;
	private Double precoLocacao;
	private Double valorAquisicao;

}
