package br.com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableCaching
@EnableAsync
@EnableScheduling
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(basePackages = { "br.com.app" })
@EnableAutoConfiguration
@SpringBootApplication
public class GestaoConstrucaoCivilApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoConstrucaoCivilApplication.class, args);
	}

	

}
