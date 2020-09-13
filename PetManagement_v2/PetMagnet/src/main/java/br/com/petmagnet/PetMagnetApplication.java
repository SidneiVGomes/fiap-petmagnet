package br.com.petmagnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PetMagnetApplication {

	
	public static void main(String[] args) {
	//System.out.println(new BCryptPasswordEncoder().encode("123456"));
		SpringApplication.run(PetMagnetApplication.class, args);
	}	

}
