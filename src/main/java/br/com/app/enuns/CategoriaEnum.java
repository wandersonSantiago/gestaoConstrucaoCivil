package br.com.app.enuns;

public enum CategoriaEnum {

	SERVICOS(1,"Serviços"),
	ESTOQUE(2, "Estoque"),
	DIVERSOS(3, "Diversos");
	
	private int codigo;
	private String descricao;
	
	CategoriaEnum(int codigo, String descricao){
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
