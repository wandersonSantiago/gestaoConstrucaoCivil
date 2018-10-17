package br.com.app.pojo;

import lombok.Data;

@Data
public class Page {

	private Integer page = 0;
	private Integer linesPerPage = 24;
	private String orderBy = "id";
	private String direction = "ASC";
	
}
