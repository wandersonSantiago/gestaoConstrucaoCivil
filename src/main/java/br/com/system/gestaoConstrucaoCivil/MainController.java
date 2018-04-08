package br.com.system.gestaoConstrucaoCivil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

	/*@RequestMapping("/usuario")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}*/
	
	@RequestMapping(value = "/{[path:[^\\.]*}")
	public String redirect() {
	  return "forward:/";
	}
	
	@RequestMapping("/home")
	public String Home(){
		
		return "index";
	}

}
