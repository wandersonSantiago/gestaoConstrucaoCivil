package br.com.app.commons.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagemService {

	@Value("${caminhoFoto}")
	private String path;
	private String profile = "/profile";
	private String login = "";
	private InputStream in;
	private static final String defaultProfile = "src/main/resources/static/public/img/avatar_2x.png";

	public void createPathAndSaveFile(MultipartFile file, String login) throws IOException {

		this.login = login;
		if (!Paths.get(this.path.concat(this.login)).toFile().exists()) {
			Files.createDirectory(Paths.get(this.path.concat(login)));
		}
	
		
		Files.write(Paths.get(this.path.concat(this.login.concat(profile))), file.getBytes());

	}

	public String getPath() {
		return path.concat(this.login).concat(profile);
	}

	public InputStream getFoto(String caminho) {

		return buscarFoto(caminho);

	}

	private InputStream buscarFoto(String caminho) {

		try {
			if (!caminho.endsWith("/")) {
				caminho = caminho.concat("/");
			}

			in = Files.newInputStream(Paths.get(caminho));
		} catch (IOException|NullPointerException e) {
			setDefaultProfile();
		}
		return in;
	}

	private void setDefaultProfile() {
		try {
			in = Files.newInputStream(Paths.get(defaultProfile));
		} catch (IOException e) {

			System.err.println(e);
		}
	}
}
