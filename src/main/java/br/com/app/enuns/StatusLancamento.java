package br.com.app.enuns;

public enum StatusLancamento {

	
	PENDENTE(1,"Pendente"),
	PAGO(2,"Pago"),
	VENCIDO(3, "Vencido");
	
	private int codigo;
	private String descricao;
	
	StatusLancamento(int codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
