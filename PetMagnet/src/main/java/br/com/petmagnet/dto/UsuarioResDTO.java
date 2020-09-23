package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.model.Usuario;

public class UsuarioResDTO {
	private Long idUsuario;	
	private String email;
	private Integer distanciaAnuncio;
	private String cep;

	@JsonIgnore
	private List<Usuario> usuarios;
	
	public List<UsuarioResDTO> toList() {
		List<UsuarioResDTO> listDTO = new ArrayList<UsuarioResDTO>();
		
		for (Usuario e : this.usuarios) {
			listDTO.add(new UsuarioResDTO(e));
		}
		
		return listDTO;
	}

	public UsuarioResDTO(Long idUsuario, String email, Integer distanciaAnuncio, String cep) {
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.distanciaAnuncio = distanciaAnuncio;
		this.cep = cep;
	}

	public UsuarioResDTO(List<Usuario> obj) {
		super();
		this.setUsuarios(obj);
	}

	public UsuarioResDTO(Usuario obj) {
		super();
		this.idUsuario = obj.getId();
		this.email = obj.getEmail();
		this.distanciaAnuncio = obj.getDistanciaAnuncio();
		this.cep = obj.getEndereco().getCep();
	}	
	
	public UsuarioResDTO() {
		super();
	}	
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDistanciaAnuncio() {
		return distanciaAnuncio;
	}

	public void setDistanciaAnuncio(Integer distanciaAnuncio) {
		this.distanciaAnuncio = distanciaAnuncio;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
