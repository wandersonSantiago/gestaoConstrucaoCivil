package br.com.app.util.gerador.codigo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.entity.CodigoGerado;
import br.com.app.repository.CodigoGeradoRepository;

@Service
public class ExecuteGera {

	@Autowired
	private CodigoGeradoRepository repository;

	public Integer gerar(Gera g) {

		Integer tentativa = 0;
		Integer codigo = 0;
		while (tentativa <= 99999) {
			codigo = g.gerar();
			if (!repository.existeCodigo(codigo)) {
				CodigoGerado codigoGerado = new CodigoGerado();
				codigoGerado.setCodigo(codigo.toString());
				codigoGerado.setDataGeracao(LocalDate.now());
				repository.save(codigoGerado);
				return codigo;
			}
			tentativa++;
		}
		throw new GeraCodigoException("Não é possivel gerar um código");

	}
}
