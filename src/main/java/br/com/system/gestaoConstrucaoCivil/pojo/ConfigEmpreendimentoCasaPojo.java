package br.com.system.gestaoConstrucaoCivil.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoCasa;

public class ConfigEmpreendimentoCasaPojo {


	private ConfigEmpreendimentoCasa casa;
	private Collection<Integer> quantidadeCasa;
	private Collection<Integer> quantidadeAndarCasa;

	public ConfigEmpreendimentoCasaPojo(ConfigEmpreendimentoCasa casa) {
		this.casa = casa;
		criar();
	}

	private void criar() {
		quantidadeCasa = criarArray(casa.getQuantidadeCasa());
		quantidadeAndarCasa = criarArray(casa.getQuantidadeAndarPorCasa());

	}

	private Collection<Integer> criarArray(Integer tamanho) {
		List<Integer> array = new ArrayList<Integer>();
		for (int i = 1; i <= tamanho; i++)
			array.add(i);

		return array;
	}

	public Collection<Integer> getQuantidadeCasa() {
		return quantidadeCasa;
	}

	public void setQuantidadeCasa(Collection<Integer> quantidadeCasa) {
		this.quantidadeCasa = quantidadeCasa;
	}

	public Collection<Integer> getQuantidadeAndarCasa() {
		return quantidadeAndarCasa;
	}

	public void setQuantidadeAndarCasa(Collection<Integer> quantidadeAndarCasa) {
		this.quantidadeAndarCasa = quantidadeAndarCasa;
	}

	
}
