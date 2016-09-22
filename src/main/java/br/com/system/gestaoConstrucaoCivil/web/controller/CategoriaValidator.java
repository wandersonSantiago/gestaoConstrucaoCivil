package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.system.gestaoConstrucaoCivil.entity.Categoria;

public class CategoriaValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Categoria.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		
		System.out.println("Chamou o validador");
      Categoria categoria = (Categoria) target;
		
		if (categoria.getDescricao().isEmpty()) {
			
				errors.rejectValue("descricao", "descricao", "Erro na descrição");

				System.out.println("Chamou o validador");
			}
		errors.rejectValue("descricao", "descricao", "Erro na descrição");

		System.out.println("Chamou o validador 2");
	}

}
