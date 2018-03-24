package br.com.system.gestaoConstrucaoCivil.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
 	
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
	
		http
		.httpBasic().and()
		.authorizeRequests()
			.antMatchers("/public/**","/views/**","/login","/views/login.html", "/index.html","/").permitAll()			
			/*.antMatchers("/public/**","/views/templates/menu.html", "/views/templates/footer.html", "/views/templates/header.html", "/views/pages/modulo_admin/empresa_contratada/js",
					"/views/pages/modulo_cadastros/area/js", "/views/pages/modulo_cadastros/fabricante/js",	"/views/pages/modulo_cadastros/fornecedor/js",
					"/views/pages/modulo_cadastros/produto/categoria/js", "/views/pages/modulo_cadastros/produto/tipo/js", "/views/pages/modulo_cadastros/produto/js",
					"/views/pages/modulo_compras/compras/js", "/views/pages/modulo_compras/cotacao/js",
					"/views/pages/modulo_estoque/js", "/views/pages/modulo_estoque/gerenciamento/js", "/views/pages/modulo_estoque/requisicao/js", "/views/pages/modulo_estoque/transferencia/js",
					"/views/pages/modulo_gerenciamento/empreendimento/configuracao/js", "/views/pages/modulo_gerenciamento/empreendimento/empreendimento/js",
					"/views/pages/modulo_gerenciamento/usuario/js",
					"/views/pages/modulo_recursos_humanos/cargo/js", "/views/pages/modulo_recursos_humanos/funcionario/js",
					"/views/pages/modulo_servicos/pacotes/js", "/views/pages/modulo_servicos/prestadora_servicos/js", "/views/pages/modulo_servicos/vincular/js",
					"/login","/views/login.html", "/index.html","/").permitAll()*/
			.anyRequest().authenticated()
			.and()
			.logout()
			.and()
		.csrf()
       .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		 
		 
//		auth
//            .inMemoryAuthentication()
//                .withUser("user").password("123").roles("USER");
	}

	

}
