package br.com.system.gestaoConstrucaoCivil.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
/*@EnableAsync(proxyTargetClass = true)
@EnableCaching(proxyTargetClass = true)*/
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	/*	@Autowired
    private UsuarioLogadoDetailService usuarioLogadoDetailService;
    */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		
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
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		//auth.userDetailsService(usuarioLogadoDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}*/
}
