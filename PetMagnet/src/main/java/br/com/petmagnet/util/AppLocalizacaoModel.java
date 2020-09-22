package br.com.petmagnet.util;

public class AppLocalizacaoModel {
	Double longitude;
	Double latitude;
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
//	public String getLongitude() {
//		return longitude;
//	}
//	public void setLongitude(String longitude) {
//		this.longitude = longitude.replaceFirst("\\.", ",");
//	}
//	public String getLatitude() {
//		return latitude;
//	}
//	public void setLatitude(String latitude) {
//		this.latitude = latitude.replaceFirst("\\.", ",");
//	}
}
