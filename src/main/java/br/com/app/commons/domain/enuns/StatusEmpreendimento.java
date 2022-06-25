package br.com.app.commons.domain.enuns;



public enum StatusEmpreendimento {
	
	EM_ANDAMENTO(1,"Em andamento"),
	ENCERRADO(2, "Encerrado")
	
	;
	
	
	private int cod;
	private String descricao;
	
	private StatusEmpreendimento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static StatusEmpreendimento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		for(StatusEmpreendimento x : StatusEmpreendimento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
	
		 	
}
