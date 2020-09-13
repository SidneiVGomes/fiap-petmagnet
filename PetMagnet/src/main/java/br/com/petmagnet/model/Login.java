package br.com.petmagnet.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Service;

@Service
public class Login {
	@NotBlank(message = "{campo.obrigatorio}")
	@Email
	String usuario;
	
	@NotBlank(message = "{campo.obrigatorio}")
	@Size(min = 6, max = 30)
	String senha;

	public Login(@NotBlank(message = "{campo.obrigatorio}") @Email String usuario,
			@NotBlank(message = "{campo.obrigatorio}") @Size(min = 6, max = 30) String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}	

	public Login() {
		super();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	
	
//	public Colaborador validarAcesso(ColaboradorService s) {
//		return s.consultarPorEmaileSenha(this.usuario, this.senha);
//	}
	
	
}
