package br.com.app.entity;

 import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.app.enuns.StatusEmpreendimento;
import lombok.Data;

@Entity
@Table(name = "empreendimento" , schema = "communs")
@SequenceGenerator(name = "empreendimento_id_seq", sequenceName = "empreendimento_id_seq",  allocationSize = 1,schema = "communs")
@Data
public class Empreendimento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empreendimento_id_seq")
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_endereco",nullable = false)
	private Endereco endereco;
	
	@Column(nullable = false,length = 50)
	private String descricao;
	
	private String telefone;
	
	@Column(nullable = false)
	private Double valorMaximoGastar;
	@Column(nullable = false)
	private Double valoresGastos = 0.0;
	@Column(nullable = false)
	private Double porcentagem =  0.0;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_abertura")
	private Date dataAbertura;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fechamento")
	private Date datafechamento;
	
    @Enumerated(EnumType.STRING)
	private StatusEmpreendimento status;
	
    @OneToOne
    private Empreendimento matriz;
    
    
    public boolean isMatriz() {
    	return matriz == null;
    	
    }
    
 
	
}
