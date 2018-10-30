package br.com.app.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.com.app.entity.Lancamento;
import br.com.app.enuns.TipoLancamentoEnum;
import lombok.Data;

@Data
public class SaldoLancamentoDTO {
	
	
	private BigDecimal saldo;
	private BigDecimal totalEntrada;
	private BigDecimal totalSaida;
	private BigDecimal entrada;
	private BigDecimal saida;
	private BigDecimal entradaFutura;
	private BigDecimal saidaFutura;
	private Date dataInicial;
    private Date dataFinal;
    private BigDecimal contasAPagarVencidas;
    private BigDecimal contasAReceberVencidas;
    private List<Lancamento> lancamentos = new ArrayList<>();
    
    
	public SaldoLancamentoDTO(BigDecimal totalEntrada,BigDecimal totalsaida, BigDecimal entrada, BigDecimal saida, BigDecimal entradaFutura,
			BigDecimal saidaFutura,BigDecimal contasAPagarVencidas, BigDecimal contasAReceberVencidas, Date dataInicial, Date dataFinal) {
		super();
		this.saldo = Optional.ofNullable(totalEntrada).orElse(BigDecimal.ZERO).subtract(Optional.ofNullable(totalsaida).orElse(BigDecimal.ZERO));
		this.entrada = entrada;
		this.saida = saida;
		this.entradaFutura = entradaFutura;
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
	/* 
	public BigDecimal getContasAPagarVencidas() {
		return calculaValorDaLista(lancamentos,TipoLancamentoEnum.DEBITO);	
	}
    
	public BigDecimal getContasAReceberVencidas() {
		return calculaValorDaLista(lancamentos, TipoLancamentoEnum.CREDITO);
	}
	
	public BigDecimal calculaValorDaLista(List<Lancamento> lanc , TipoLancamentoEnum tipo) {
		lanc.removeIf((Lancamento item) -> !item.getTipo().equals(tipo));
		List<Lancamento> itens = lanc;
		return itens.stream()
				.map(Lancamento::getValor)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}*/
	
	
	public BigDecimal getSaldo() {
		if(saldo == null) {
			return BigDecimal.ZERO;
		}
		return saldo;
	}

	public BigDecimal getTotalEntrada() {
		if(totalEntrada == null) {
			return BigDecimal.ZERO;
		}
		return totalEntrada;
	}

	public BigDecimal getTotalSaida() {
		if(totalSaida == null) {
			return BigDecimal.ZERO;
		}
		return totalSaida;
	}

	public BigDecimal getEntrada() {
		if(entrada == null) {
			return BigDecimal.ZERO;
		}
		return entrada;
	}

	public BigDecimal getSaida() {
		if(saida == null) {
			return BigDecimal.ZERO;
		}
		return saida;
	}

	public BigDecimal getEntradaFutura() {
		if(entradaFutura == null) {
			return BigDecimal.ZERO;
		}
		return entradaFutura;
	}

	public BigDecimal getSaidaFutura() {
		if(saidaFutura == null) {
			return BigDecimal.ZERO;
		}
		return saidaFutura;
	}
    
    
}
