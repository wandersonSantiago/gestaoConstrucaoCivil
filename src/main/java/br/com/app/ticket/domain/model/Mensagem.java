package br.com.app.ticket.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.commons.domain.model.Usuario;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "mensagem_id_seq", sequenceName = "mensagem_id_seq", allocationSize = 1, schema = "chamado")
@Table(name = "mensagem", schema = "chamado")
public class Mensagem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mensagem_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_chamado")
	private Chamado chamado;

	@Column(name = "texto")
	private String texto;

	@Column(name = "data")
	private Date data;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	protected Usuario usuario;

	@Column(name = "arquivo")
	private Byte arquivo;

}
