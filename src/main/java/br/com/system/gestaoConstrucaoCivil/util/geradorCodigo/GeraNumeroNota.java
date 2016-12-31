package br.com.system.gestaoConstrucaoCivil.util.geradorCodigo;

import org.springframework.stereotype.Service;

@Service
public class GeraNumeroNota extends GeraCodigo{

	private Integer numeroNota;
	public GeraNumeroNota() {
		super(100000,999999);
		// TODO Auto-generated constructor stub
	}
   public Integer gerarNumeroNota()
   {
	   numeroNota = gerarNumero();
	   return numeroNota;
   }
}
