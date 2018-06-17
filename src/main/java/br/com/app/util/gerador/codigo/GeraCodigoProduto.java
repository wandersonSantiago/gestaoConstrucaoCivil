package br.com.app.util.gerador.codigo;

public class GeraCodigoProduto extends GeraCodigo implements Gera {

	public GeraCodigoProduto() {

		super(100000, 999999);

	}

	public String gerar() {

		return gerarNumero().toString() ;
	}

}
