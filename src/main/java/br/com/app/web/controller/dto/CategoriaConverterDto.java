package br.com.app.web.controller.dto;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

import br.com.app.entity.Categoria;
import br.com.app.web.controller.almoxarifado.dto.CategoriaDTO;

public class CategoriaConverterDto implements Converter<CategoriaDTO, Categoria> {

	@Override
	public Categoria convert(CategoriaDTO categoriaDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(categoriaDTO, Categoria.class);
	}

}
