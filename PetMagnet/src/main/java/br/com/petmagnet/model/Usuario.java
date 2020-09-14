package br.com.petmagnet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends LogRegistro {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_usuario")
	@Column(name = "id_usuario")
	private Long id;	

	@Column(name = "ds_email", length = 60)
	@NotEmpty
	@Email
	private String email;

	@Column(name = "ds_senha", length = 20)
	@NotEmpty
	@Length(min = 5, max = 20, message = "A senha deve ter entre {min} a {max} caracteres")
	private String senha;

	@Column(name = "nr_distancia_anuncio")
	private Integer distanciaAnuncio;

	public Usuario(Long id, @NotEmpty @Email String email,
			@NotEmpty @Length(min = 5, max = 20, message = "A senha deve ter entre {min} a {max} caracteres") String senha,
			Integer distanciaAnuncio) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.distanciaAnuncio = distanciaAnuncio;
	}

	public Usuario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getDistanciaAnuncio() {
		return distanciaAnuncio;
	}

	public void setDistanciaAnuncio(Integer distanciaAnuncio) {
		this.distanciaAnuncio = distanciaAnuncio;
	}
}
