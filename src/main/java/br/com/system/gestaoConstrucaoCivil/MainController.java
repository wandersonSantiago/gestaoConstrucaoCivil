package br.com.system.gestaoConstrucaoCivil;

import java.security.Principal;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
