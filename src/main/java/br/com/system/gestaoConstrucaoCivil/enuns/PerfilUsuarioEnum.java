package br.com.system.gestaoConstrucaoCivil.enuns;

public enum PerfilUsuarioEnum {
	
	ADMIN("administrador do sistema"),
	ADMINISTRADOR_EMPRESA("administrador da empresa"),
	ESTOQUE_CADASTRO("cadastro estoque"),
	ESTOQUE_CONSULTA("consulta estoque"),
	COTACAO_CADASTRO("cadastro cotação"),
	COTACAO_CONSULTA(" consulta cotação"),
	COMPRAS_CADASTRO(" cadastro de compras"),	
	GESTOR("gestor");
	
	 private String descricao;
	
	PerfilUsuarioEnum(String descricao) {
        this.descricao = descricao;
    }

	public String getDescricao() {
		return descricao;
	}

	
	

}
