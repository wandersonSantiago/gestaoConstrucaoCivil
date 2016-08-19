package br.com.system.gestaoConstrucaoCivil;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
