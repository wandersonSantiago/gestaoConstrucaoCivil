package br.com.app.estoque.api.v1.resource;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.app.commons.domain.model.Categoria;

public class CategoriaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Categoria.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Categoria categoria = (Categoria) target;

		if (categoria.getDescricao().isEmpty()) {

			errors.rejectValue("descricao", "descricao", "Erro na descrição");

		}
		errors.rejectValue("descricao", "descricao", "Erro na descrição");

	}

}
