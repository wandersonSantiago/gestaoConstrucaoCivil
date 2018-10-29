package br.com.app.pojo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import lombok.Data;

@Data
public class Page {

	private Integer page = 0;
	private Integer linesPerPage = 24;
	private String orderBy = "id";
	private String direction = "ASC";

	public Pageable getPageRequest() {
		return PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
	}

}
