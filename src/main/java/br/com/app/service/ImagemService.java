package br.com.app.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.app.enuns.ImageType;

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
	
	public  byte[] resizeImage(byte[] imageByte, int width, int height) throws IOException {
	    try (InputStream inputImage = new ByteArrayInputStream(imageByte);) {
	        BufferedImage image = ImageIO.read(inputImage);
	        BufferedImage imgResized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        imgResized.getGraphics().drawImage(image, 0, 0, width, height, null);
	        return imageToByte(imgResized);
	    }
	}
	
	private  byte[] imageToByte(BufferedImage img) throws IOException {
	    return imageToByte(img, ImageType.JPEG);
	}
	
	private  byte[] imageToByte(BufferedImage img, ImageType tipoImagem) throws IOException {
	    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
	        ImageIO.write(img, tipoImagem.getValue(), baos);
	        baos.flush();
	        return baos.toByteArray();
	    }
	}

}
