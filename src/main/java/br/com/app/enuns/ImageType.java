package br.com.app.enuns;

public enum ImageType {

	JPG("jpg"), PNG("png"), JPEG("jpeg");
	
	private String value;
	
	ImageType(String descricao){
		this.value = descricao;
	}

	public String getValue() {
		return value;
	}
	
	
}
