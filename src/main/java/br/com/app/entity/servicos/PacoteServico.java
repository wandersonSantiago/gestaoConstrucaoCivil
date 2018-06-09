package br.com.app.entity.servicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.entity.Categoria;
import br.com.app.util.gerador.codigo.GeraCodigoPacoteServico;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "pacote_servico_id_seq", sequenceName = "pacote_servico_id_seq", allocationSize = 1, schema = "servicos")
@Table(name = "pacote_servico", schema = "servicos")
public class PacoteServico {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pacote_servico_id_seq")
	private Long id;

	@Column(nullable = false)
	private boolean ativo;

	@Column(nullable = false)
	private Integer codigo;

	@Column(nullable = false, length = 50)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = true)
	private Categoria categoria;
	@Column(nullable = false, length = 250)
	private String servicosAtribuidos;

	@Column(nullable = false, length = 250)
	private String criterioServico;

	@Column(nullable = false)
	private Double valor;

	public void novoPacote() {
		this.codigo = new GeraCodigoPacoteServico().gerarNumeroPacote();

	}

}
