package br.com.app.web.controller.dto;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.app.entity.Categoria;
import br.com.app.web.controller.almoxarifado.dto.CategoriaDTO;

@Component
public class CategoriaConverterModel implements Converter<Categoria, CategoriaDTO> {

	@Override
	public CategoriaDTO convert(Categoria categoria) {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.createTypeMap(Categoria.class, CategoriaDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getDescricao(), CategoriaDTO::setDescricao);

		});
		return modelMapper.map(categoria, CategoriaDTO.class);
	}

}
