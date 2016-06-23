package br.com.system.gestaoConstrucaoCivil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableAsync
@EnableScheduling
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(basePackages = {"br.com.system.gestaoConstrucaoCivil"})
@EnableAutoConfiguration
@SpringBootApplication
public class GestaoConstrucaoCivilApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(GestaoConstrucaoCivilApplication.class, args);
	}
	
	
}
