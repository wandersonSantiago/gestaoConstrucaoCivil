package br.com.system.gestaoConstrucaoCivil.enuns;



public enum TipoEmpreendimentoEnum {
	EDIFICIO_COMERCIAL("Edifício comercial"),
	EDIFICIO_RESIDENCIAL("Edifício residencial"), 
	CONDOMINIO_DE_CASA("Condomínio de casas"), 
	CONDOMINIO_DE_EDIFICIO_RESIDENCIAL("Condomínio de edifício residencial"), 
	CONDOMINIO_DE_EDIFICIO_COMERCIAL("Condomínio de edifício comercial"), 
	OUTRO("Outro");

	private String descricao;

	TipoEmpreendimentoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
}
