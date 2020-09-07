package br.com.petmagnet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.petmagnet.config.GeoLocationProperties;

@SpringBootApplication
public class PetMagnetApplication {
	@Autowired
	GeoLocationProperties glp;
	
	public static void main(String[] args) {
		SpringApplication.run(PetMagnetApplication.class, args);
	}	
	
	@PostConstruct
	public void configuracaoExterna() {
		System.out.println(glp.getApiKey());
	}
	
}
