package br.com.petmagnet.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocationProperties {
	String apiKey;

	public GeoLocationProperties(String apiKey) {
		super();
		this.apiKey = apiKey;
	}

	public GeoLocationProperties() {
		super();
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	} 
}
