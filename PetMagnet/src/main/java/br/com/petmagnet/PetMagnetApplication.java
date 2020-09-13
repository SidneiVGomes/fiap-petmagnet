package br.com.petmagnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetMagnetApplication {
//	@Autowired
//	GeoLocationProperties glp;
	
	public static void main(String[] args) {
		SpringApplication.run(PetMagnetApplication.class, args);
	}	
	
//	@PostConstruct
//	public void configuracaoExterna() {
//		System.out.println(glp.getApiKey());
//	}
}
