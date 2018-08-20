package br.com.app.web.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.app.entity.Usuario;

@Component
public class UsuarioConverterModel implements Converter<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO convert(Usuario usuario) {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.createTypeMap(Usuario.class, UsuarioDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getEmpreendimento().getDescricao(), UsuarioDTO::setEmpreendimento);
		});
		return modelMapper.map(usuario, UsuarioDTO.class);

	}

	public List<UsuarioDTO> converter(List<Usuario> usuarios) {

		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		usuarios.stream().forEach(usuario -> usuariosDTO.add(convert(usuario)));
		return usuariosDTO;

	}
}
