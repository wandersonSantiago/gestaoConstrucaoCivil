package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;
import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonView;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.View.Summary;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusRequisicao;
import br.com.system.gestaoConstrucaoCivil.util.GeraCodigo;


@Entity
@SequenceGenerator(name = "requisicao_id_seq",
sequenceName = "requisicao_id_seq",
initialValue = 1,
allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  abstract class Requisicao implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "requisicao_id_seq")
	protected Long id;
	
	@JsonView(Summary.class)
	@Column(name = "numero_requisicao")
	protected Integer numeroRequisicao;
	
	@JsonView(Summary.class)
	@OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL)
	protected List<ItemRequisicao> item;
	
	@JsonView(Summary.class)
    protected LocalDate dataSaida;
	
	@JsonView(Summary.class)
    @Enumerated(EnumType.STRING)
	protected StatusRequisicao statusRequisicao;
	
    @ManyToOne
    @JoinColumn(name="id_empreendimento")
    protected Empreendimento empreendimento;
   	
    public Requisicao()
    {
    	this.statusRequisicao = StatusRequisicao.PENDENTE;
        GeraCodigo gerar = new GeraCodigo(100000,999999);
		this.numeroRequisicao = gerar.gerarNumeroRequisicao();
		
    }
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getNumeroRequisicao() {
		return numeroRequisicao;
	}
	public void setNumeroRequisicao(Integer numeroRequisicao) {
		this.numeroRequisicao = numeroRequisicao;
	}
	public List<ItemRequisicao> getItem() {
		return item;
	}
	public void setItem(List<ItemRequisicao> item) {
		this.item = item;
	}
    public LocalDate getDataSaida() {
		return dataSaida;
	}
	public StatusRequisicao getStatusRequisicao() {
		return statusRequisicao;
	}
	public void setStatusRequisicao(StatusRequisicao statusRequisicao) {
		this.statusRequisicao = statusRequisicao;
	}
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}
	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
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
		Requisicao other = (Requisicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
       
	
}
