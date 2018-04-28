package br.com.app.pojo;

public class InformacaoEntradaProduto {

	private Double  valorTotal;
	private Integer quantidadeTotalNota;

	public InformacaoEntradaProduto(Double  valorTotal,Integer quantidadeTotalNota){
		
		this.valorTotal = valorTotal;
		this.quantidadeTotalNota = quantidadeTotalNota;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}
	public Integer getQuantidadeTotalNota() {
		return quantidadeTotalNota;
	}
	
}
