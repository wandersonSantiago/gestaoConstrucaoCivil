package br.com.system.gestaoConstrucaoCivil.pojo;

import java.util.ArrayList;
import java.util.List;

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoEdificio;

public class ConfigEmpreendimentoEdificioPojo {

	private ConfigEmpreendimentoEdificio edificio;
	private List<Integer> quantidadeTorres;
	private List<Integer> quantidadeAndares;
	private List<Integer> quantidadeApartamentos;

	public ConfigEmpreendimentoEdificioPojo(ConfigEmpreendimentoEdificio edificio) {
		this.edificio = edificio;
		criar();
	}

	private void criar() {
		quantidadeTorres = criarArray(edificio.getQuantidadeTorre());
		quantidadeAndares = criarArray(edificio.getQuantidadeAndarPorTorre());
		quantidadeApartamentos = criarArray(edificio.getQuantidadeApartamentoPorAndar());

	}

	private List<Integer> criarArray(Integer tamanho) {
		List<Integer> array = new ArrayList<Integer>();
		for (int i = 1; i < tamanho; i++)
			array.add(i);

		return array;
	}

	public List<Integer> getQuantidadeTorres() {

		return quantidadeTorres;
	}

	public List<Integer> getQuantidadeAndares() {
		return quantidadeAndares;
	}

	public List<Integer> getQuantidadeApartamentos() {
		return quantidadeApartamentos;
	}
}
