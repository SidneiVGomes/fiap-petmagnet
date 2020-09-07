package br.com.petmagnet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application-apikey.properties")
public class ConfiguracoesExternas {

	@Autowired
	private Environment env;	

	@Bean
    public GeoLocationProperties geoLocationProperties() {
		return new GeoLocationProperties(this.env.getProperty("app.geolocation.apikey"));
	}
}
