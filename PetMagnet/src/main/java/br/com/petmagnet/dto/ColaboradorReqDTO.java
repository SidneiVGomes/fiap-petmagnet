package br.com.petmagnet.dto;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Colaborador;

public class ColaboradorReqDTO implements RequestDTO<Colaborador> {
	@Length(min = 3, message = "O Nome deve ter no mínimo 3 caracteres")
	private String nome;
	private String senha;
	private Long idEstabelecimento;
	
	@Override
	public Colaborador toEntity() {
		return new ModelMapper().map(this, Colaborador.class);
	}

	public ColaboradorReqDTO(@Length(min = 3, message = "O Nome deve ter no mínimo 3 caracteres") String nome,
			String senha, Long idEstabelecimento) {
		super();
		this.setNome(nome);
		this.setSenha(senha);
		this.setIdEstabelecimento(idEstabelecimento);
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
