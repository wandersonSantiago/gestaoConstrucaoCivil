package br.com.app.enuns;

public enum CategoriaEnum {

	CUSTOS_DESPESAS_FIXAS(1,"Custos e Despesas Fixos"),
	CUSTOS_DESPESAS_VARIAVEIS(2, "Custos e Despesas Variáveis"),
	OPERACOES_FINANCERIAS(3, "Operações Financeiras"),
	RECEITAS(4, "Receitas");
	
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
