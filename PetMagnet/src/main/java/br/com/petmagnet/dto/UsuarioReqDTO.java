package br.com.petmagnet.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Usuario;

public class UsuarioReqDTO implements RequestDTO<Usuario> {
	@JsonIgnore
	private Long idUsuario;
	@Email
	@NotEmpty(message = "Informe seu email para consuir o cadastro.")
	private String email;
	@NotEmpty
	@Length(min = 5, max = 20, message = "A senha deve ter entre {min} a {max} caracteres")
	private String senha;
	private Integer distanciaAnuncio;
	
	@Override
	public Usuario toEntity() {
		return new ModelMapper().map(this, Usuario.class);
	}

	public UsuarioReqDTO(Long idUsuario,
			@Email @NotEmpty(message = "Informe seu email para consuir o cadastro.") String email,
			@NotEmpty @Length(min = 5, max = 20, message = "A senha deve ter entre {min} a {max} caracteres") String senha,
			Integer distanciaAnuncio) {
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.senha = senha;
		this.distanciaAnuncio = distanciaAnuncio;
	}

	public UsuarioReqDTO() {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	
}
