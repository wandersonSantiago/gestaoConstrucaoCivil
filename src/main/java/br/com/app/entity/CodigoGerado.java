package br.com.app.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "codigo_gerado_id_seq", sequenceName = "codigo_gerado_id_seq", allocationSize = 1, schema = "communs")
@Table(name = "codigo_gerado", schema = "communs")
public class CodigoGerado {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigo_gerado_id_seq")
	private Long id;
	private String codigo;
	@Column(name = "data_geracao")
	private LocalDate data_geracao;
}
