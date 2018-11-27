package br.com.app.web.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.app.entity.Usuario;
import br.com.app.enuns.StatusUsuarioEnum;
import br.com.app.pojo.MensagemException;
import br.com.app.service.ImagemService;
import br.com.app.service.UsuarioService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/rest/usuario")
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ImagemService imagemService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/usuario")
	@ResponseBody
	public Usuario user(Principal user, HttpSession session) {

		return (Usuario) session.getAttribute("usuario");
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/descricao")
	public Page<Usuario> findByDescricao(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "descricao", required = false, defaultValue = "") String descricao) {

		Page<Usuario> list = null;

		if (descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = usuarioService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		} else {
			list = usuarioService.findByDescricaoIgnoreCase(descricao,
					PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		return list;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Usuario insert(@Validated @RequestBody Usuario usuario) {

		return usuarioService.insert(usuario);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public Usuario update(@RequestBody Usuario usuario) {
		return usuarioService.update(usuario);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/{idUsuario}/senha/{senha}/nova-senha/{novaSenha}")
	public void updatePassword(@PathVariable Long idUsuario, @PathVariable String senha,
			@PathVariable String novaSenha) {
		usuarioService.updatePassword(idUsuario, senha, novaSenha);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/{idEmpreendimento}")
	public void updateEmpreendimento(@PathVariable Long idEmpreendimento) {
		usuarioService.updateEmpreendimento(idEmpreendimento);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Usuario buscarPorId(@PathVariable Long id) {
		return usuarioService.findById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/existeLogin/{login}")
	public Boolean verificarSeExisteLogin(@PathVariable String login) {
		return usuarioService.existeLoginCadastrado(login);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/status")
	public Collection<StatusUsuarioEnum> status() {
		return Arrays.asList(StatusUsuarioEnum.values());
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/foto")
	public void recebeImagem(@RequestPart("file") MultipartFile file, @RequestPart("usuario") Usuario usuario) {

		try {
				if(file != null && !file.isEmpty()) {
					byte[] bytes = imagemService.resizeImage(file.getBytes(), 300, 400);
					String base64 = Base64.encodeBase64String(bytes);
					usuario.setCaminhoFoto(base64);
				}else {
					usuario.setCaminhoFoto(null);
				}
		/*	
			imagemService.createPathAndSaveFile(file, usuario.getLogin());
			usuario.setCaminhoFoto(imagemService.getPath());
			usuarioService.savePathFoto(usuario);*/
				usuarioService.savePathFoto(usuario);

		} catch (Exception e) {
		
			throw new MensagemException("Não foi possivel salvar a foto" + e.getMessage());
		}
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}/foto", produces = MediaType.IMAGE_JPEG_VALUE)
	public InputStreamResource getFoto(@PathVariable Long id) throws IOException, NotFoundException {
		
		Usuario user = usuarioService.findById(id);
		
		return new InputStreamResource(imagemService.getFoto(user.getCaminhoFoto()));
	}

}
