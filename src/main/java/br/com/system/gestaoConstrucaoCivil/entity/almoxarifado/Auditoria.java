package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoMovimentacaoEnum;

@Entity
@SequenceGenerator(name = "estoque_auditoria_id_seq", sequenceName = "estoque_auditoria_id_seq", initialValue = 1, allocationSize = 1)
@Table(name = "estoque_auditoria")
public class Auditoria{
	
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estoque_auditoria_id_seq")
	Long id;
	
	private Date dataCadastro;
	@Enumerated(EnumType.STRING)
	private TipoMovimentacaoEnum tipoMovimentacao;
	
	@ManyToOne
	@JoinColumn(name = "id_empreendimento",nullable = false)
	private Empreendimento empreendimento;
	
	@ManyToOne
	@JoinColumn(name ="id_usuario_cadastro")
	private Usuario usuarioCadastro;

	@OneToOne
	@JoinColumn(name ="id_item")
	private Item item;
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoMovimentacaoEnum getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacaoEnum tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	
	
}
