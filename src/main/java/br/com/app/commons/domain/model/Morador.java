package br.com.app.commons.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "morador_id_seq", sequenceName = "morador_id_seq", allocationSize = 1,schema = "communs")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "morador", schema = "communs")
public class Morador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "morador_id_seq")
	private Long id;
	private String nomeCompleto;
	@CPF
	private String cpf;
	private String telefoneFixo;
	private String telefoneCelular;
	private String email;
	@ManyToOne
	@JoinColumn(name = "id_empreendimento", nullable = true)
	private Empreendimento empreendimento;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;

}
