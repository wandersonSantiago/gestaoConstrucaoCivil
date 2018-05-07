package br.com.app.entity.chamado;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.entity.Empreendimento;
import br.com.app.entity.Usuario;
import br.com.app.enuns.chamado.PrioridadeChamado;
import br.com.app.enuns.chamado.StatusChamado;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "chamado_id_seq", sequenceName = "chamado_id_seq", allocationSize = 1, schema = "chamado")
@Table(name = "chamado", schema = "chamado")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Chamado {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chamado_id_seq")
	private Long id;

	@Enumerated(EnumType.STRING)
	protected StatusChamado status;

	@Column(name = "titulo")
	protected String titulo;

	@Column(name = "lido")
	protected Boolean lido;

	@Column(name = "silenciar")
	protected Boolean silenciar;

	@Column(name = "data_abertura")
	protected Date dataAbertura;

	@Column(name = "data_fechamento")
	protected Date dataFechamento;

	@ManyToOne
	@JoinColumn(name = "id_empreendimento")
	protected Empreendimento empreendimento;

	@Enumerated(EnumType.STRING)
	protected PrioridadeChamado prioridade;

	@ManyToOne
	@JoinColumn(name = "id_usuarioSolicitante")
	protected Usuario usuarioSolicitante;

	@ManyToOne
	@JoinColumn(name = "id_usuarioAtendente")
	protected Usuario usuarioAtendente;

	@OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL)
	protected List<Mensagem> mensagens;

}
