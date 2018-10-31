package br.com.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.com.app.enuns.StatusUsuarioEnum;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq", allocationSize = 1, schema = "communs")
@NamedEntityGraph(name = "Usuario.detail", attributeNodes = {
		@NamedAttributeNode(value = "empreendimento", subgraph = "empreendimento"),
		@NamedAttributeNode("permissoes") }, subgraphs = {
				@NamedSubgraph(name = "empreendimento", attributeNodes = @NamedAttributeNode("matriz")),
				@NamedSubgraph(name = "empreendimento", attributeNodes = @NamedAttributeNode("endereco"))})
@Table(name = "usuario", schema = "communs")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_empreendimento", nullable = true)
	private Empreendimento empreendimento;

	@Column(length = 50)
	@NotNull(message = "campo nome obrigatório")
	private String nome;
	@Column(length = 50, unique = false)
	@NotNull(message = "campo login obrigatório")
	private String login;
	@Column(length = 40)
	@NotNull(message = "campo e-mail obrigatório")
	private String email;
	@Column(length = 256)
	@NotNull(message = "campo senha obrigatório")
	private String senha;
	@Column(nullable = false)
	private boolean ativo;

	private String caminhoFoto;

	@Enumerated(EnumType.STRING)
	private StatusUsuarioEnum status;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro")
	private Date dataCadastro = new Date();

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissoes", schema = "communs", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_permissoes"))
	private Set<Permissao> permissoes;

	@Transient
	private boolean isRoot = false;

	public boolean isRoot() {
		if (login.equalsIgnoreCase("root")) {
			isRoot = true;
			return true;
		}
		return false;
	}

	public Usuario() {
	}
	public Usuario(Long id, Empreendimento empreendimento, String nome,	 String login, String email, String senha, boolean ativo, String caminhoFoto,
			StatusUsuarioEnum status, Date dataCadastro) {
		super();
		this.id = id;
		this.empreendimento = empreendimento;
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
		this.caminhoFoto = caminhoFoto;
		this.status = status;
		this.dataCadastro = dataCadastro;
	}

	
	
	
}
