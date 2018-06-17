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
import br.com.app.enuns.StatusCotacao;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "cotacao_id_seq", sequenceName = "cotacao_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "cotacao", schema = "almoxarifado")
public class Cotacao implements Serializable {

	private static final long serialVersionUID = -428169907557000541L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_id_seq")
	private Long id;

	@Column(nullable = false)
	private String tema;
	@OneToMany(mappedBy = "cotacao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CotacaoItem> itens;
	@ManyToOne
	@JoinColumn(name = "id_empreendimento", nullable = false)
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

	public void adicionarCotacaoNoItem() {

		for (CotacaoItem item : itens) {
			item.setCotacao(this);
		}
	}

}