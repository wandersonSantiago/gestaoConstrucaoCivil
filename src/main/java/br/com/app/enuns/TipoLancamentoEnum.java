package br.com.app.enuns;

public enum TipoLancamentoEnum {
	
	DEBITO(1,"Serviços"),
	CREDITO(2, "Estoque");
	
	private int codigo;
	private String descricao;
	
	TipoLancamentoEnum(int codigo, String descricao){
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
