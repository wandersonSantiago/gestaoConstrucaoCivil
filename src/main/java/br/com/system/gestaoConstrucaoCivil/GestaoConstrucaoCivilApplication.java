package br.com.system.gestaoConstrucaoCivil;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;




@Configuration
@EnableAsync
@EnableScheduling
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(basePackages = {"br.com.system.gestaoConstrucaoCivil"})
@EnableAutoConfiguration
@SpringBootApplication
@Controller
public class GestaoConstrucaoCivilApplication {
	

	/*// Match everything without a suffix (so not a static resource)
	@RequestMapping(value = "/{path:[^\\.]*}")
	public String redirect() {
		// Forward to home page so that route is preserved.
		return "forward:/";
	}*/

	

/*	@RequestMapping("/resource")
	@ResponseBody
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}*/
	
	

	public static void main(String[] args) {
		SpringApplication.run(GestaoConstrucaoCivilApplication.class, args);
	}
	
	 

	
	/*@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			System.out.println("OK CHAMANDO");
			http
				.httpBasic().and()
				.authorizeRequests()
					.antMatchers(
							"/", "/login", "/message", "/home").permitAll()
					.anyRequest().authenticated()
					.and()
					.logout()
					.and()
				.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
			// @formatter:on
		}
	}
	*/
}
