package br.com.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

	@RequestMapping(value = "/{[path:[^\\.]*}")
	public String redirect() {
	  return "forward:/";
	}
	

}
