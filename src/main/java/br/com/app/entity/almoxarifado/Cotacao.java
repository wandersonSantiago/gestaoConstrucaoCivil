package br.com.app.entity.almoxarifado;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.app.entity.Empreendimento;
import br.com.app.entity.Usuario;
import br.com.app.enuns.StatusCotacao;
import br.com.app.pojo.SessionUsuario;

@Entity
@SequenceGenerator(name = "cotacao_id_seq", sequenceName = "cotacao_id_seq",schema="almoxarifado")
@Table(name = "cotacao", schema="almoxarifado")
public class Cotacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_id_seq")
	private Long id;
	
	@Column(nullable = false)
	private String tema;	
	@OneToMany(mappedBy = "cotacao",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<CotacaoItem> itens;
	@ManyToOne
	@JoinColumn(name = "id_empreendimento",nullable = false)
	private Empreendimento empreendimento;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_limite")
	private Date dataLimite;
	@Enumerated(EnumType.STRING)
	private StatusCotacao statusCotacao;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_criacao")
	private Date dataCriacao;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fechamento")
	private Date dataFechamento;
	
	@ManyToOne
	@JoinColumn(name ="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<CotacaoItem> getItens() {
		return itens;
	}

	public void setItens(List<CotacaoItem> itens) {
		this.itens = itens;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public StatusCotacao getStatusCotacao() {
		return statusCotacao;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void abrir()
    {
    	this.dataCriacao = new Date();
    	this.statusCotacao = StatusCotacao.ABERTO;
    	Usuario usuarioSessao = SessionUsuario.getInstance().getUsuario();
    	this.empreendimento = usuarioSessao.getEmpreendimento();
    	this.usuarioCadastro =usuarioSessao;
    	adicionarCotacaoNoItem();
    }
	private void adicionarCotacaoNoItem() {
		
		for(CotacaoItem item : itens)
		{
			item.setContacao(this);
		}
	}
    public void fechar()
    {
    	this.statusCotacao = StatusCotacao.FECHADO;
    	this.dataFechamento = new Date();
    }
    
    
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cotacao other = (Cotacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}