package br.com.petmagnet.config;

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
