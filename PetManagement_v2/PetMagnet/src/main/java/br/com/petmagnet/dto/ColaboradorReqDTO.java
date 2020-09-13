package br.com.petmagnet.dto;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorReqDTO implements RequestDTO {
	@Length(min = 3, message = "O Nome deve ter no mínimo 3 caracteres")
	private String nome;
	private String senha;
	private Long idEstabelecimento;
	
	@Override
	public Object toEntity() {
		return new ModelMapper().map(this, Colaborador.class);
	}

	public ColaboradorReqDTO(@Length(min = 3, message = "O Nome deve ter no mínimo 3 caracteres") String nome,
			String senha, Long idEstabelecimento) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.idEstabelecimento = idEstabelecimento;
	}

	public ColaboradorReqDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}
}
