package br.com.petmagnet.util;
 
public class AppLocalizacaoModel {
	String longitude;
	String latitude;
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude.replaceFirst("\\.", ",");
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude.replaceFirst("\\.", ",");
	}
}
