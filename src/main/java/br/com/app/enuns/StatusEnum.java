package br.com.app.enuns;

public enum StatusEnum {

	ATIVO(1,"Ativo"),
	INATIVO(2, "Inativo")
	;
	
	private Integer codigo;
	private String descricao;
	
	private StatusEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusEnum toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		for(StatusEnum x :StatusEnum.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status não encontrado!");
	}
}
