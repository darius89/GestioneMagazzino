package it.plansoft.gestionemagazzino;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "it.plansoft.gestionemagazzino.repositories")
public class GestioneMagazzino {
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	@Bean
	public DtoUtility utility() {
		return new DtoUtility();
	}
	public static void main(String[] args) {
		SpringApplication.run(GestioneMagazzino.class, args);
	}
	

}
