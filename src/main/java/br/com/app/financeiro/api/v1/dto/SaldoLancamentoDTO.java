package br.com.app.financeiro.api.v1.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.com.app.financeiro.domain.model.Lancamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaldoLancamentoDTO {

	private BigDecimal saldo = BigDecimal.ZERO;
	private BigDecimal totalEntrada = BigDecimal.ZERO;
	private BigDecimal totalSaida = BigDecimal.ZERO;
	private BigDecimal entrada = BigDecimal.ZERO;
	private BigDecimal saida = BigDecimal.ZERO;
	private BigDecimal entradaFutura = BigDecimal.ZERO;
	private BigDecimal saidaFutura = BigDecimal.ZERO;
	private Date dataInicial;
	private Date dataFinal;
	private BigDecimal contasAPagarVencidas = BigDecimal.ZERO;
	private BigDecimal contasAReceberVencidas = BigDecimal.ZERO;
	private List<Lancamento> lancamentos = new ArrayList<>();

	public SaldoLancamentoDTO(BigDecimal totalEntrada, BigDecimal totalsaida, BigDecimal entrada, BigDecimal saida,
			BigDecimal entradaFutura, BigDecimal saidaFutura, BigDecimal contasAPagarVencidas,
			BigDecimal contasAReceberVencidas, Date dataInicial, Date dataFinal) {
		super();
		this.saldo = Optional.ofNullable(totalEntrada).orElse(BigDecimal.ZERO)
				.subtract(Optional.ofNullable(totalsaida).orElse(BigDecimal.ZERO));
		this.entrada = entrada;
		this.saida = saida;
		this.entradaFutura = Optional.ofNullable(entradaFutura).orElse(BigDecimal.ZERO);
		this.saidaFutura = saidaFutura;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.contasAPagarVencidas = contasAPagarVencidas;
		this.contasAReceberVencidas = contasAReceberVencidas;
	}

	public BigDecimal getSaldoPrevisto() {
		return saldo.add(Optional.ofNullable(entradaFutura).orElse(BigDecimal.ZERO))
				.subtract(Optional.ofNullable(saidaFutura).orElse(BigDecimal.ZERO));
	}

}
