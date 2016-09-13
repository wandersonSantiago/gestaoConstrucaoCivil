package br.com.system.gestaoConstrucaoCivil.view;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;

public class UsuarioView {

	private String nome;
	private String empreendimento;
	
	public UsuarioView(Usuario usuario, Empreendimento empreendimento ){
		this.nome = usuario.getNome();
		
		if(empreendimento != null){
			this.empreendimento = empreendimento.getDescricao();
		}else {
			this.empreendimento = "Selecione  o Empreendimento!";
		}
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(String empreendimento) {
		this.empreendimento = empreendimento;
	}
	
	

}
