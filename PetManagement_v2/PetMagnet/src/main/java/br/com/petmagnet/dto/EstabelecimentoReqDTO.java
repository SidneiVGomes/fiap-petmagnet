package br.com.petmagnet.dto;

import org.modelmapper.ModelMapper;

import br.com.petmagnet.model.Estabelecimento;
import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class EstabelecimentoReqDTO {
	private String cnpj;
	private String nome;
	private String complEndereco;
	private EnderecoReqDTO endereco;
	
	public Estabelecimento toEntity() {
		return new ModelMapper().map(this, Estabelecimento.class);
	}

	public EstabelecimentoReqDTO(String cnpj, String nome, String complEndereco, EnderecoReqDTO endereco) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
		this.complEndereco = complEndereco;
		this.endereco = endereco;
	}

	public EstabelecimentoReqDTO() {
		super();
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComplEndereco() {
		return complEndereco;
	}

	public void setComplEndereco(String complEndereco) {
		this.complEndereco = complEndereco;
	}

	public EnderecoReqDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoReqDTO endereco) {
		this.endereco = endereco;
	}
}
