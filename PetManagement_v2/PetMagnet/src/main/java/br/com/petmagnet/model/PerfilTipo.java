package br.com.petmagnet.model;

public enum PerfilTipo {
	ADMIN(1, "ADMIN"), 
	MEDICO(2, "USUARIO");
	
	private long cod;
	private String desc;

	private PerfilTipo(long cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public long getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}
}
