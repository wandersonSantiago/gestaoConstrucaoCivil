package br.com.app.infraestructure.exception;

import lombok.Getter;

@Getter
public enum ProblemType {

	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://gestaoconstrucaocivil.com.br" + path;
		this.title = title;
	}
	
}
