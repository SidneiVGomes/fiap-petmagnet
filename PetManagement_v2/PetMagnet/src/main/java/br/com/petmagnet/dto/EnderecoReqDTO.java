package br.com.petmagnet.dto;

import org.modelmapper.ModelMapper;

import br.com.petmagnet.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoReqDTO {
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String UF;
	private String pais;
	private String cep;

	public Endereco toEntity() {
		return new ModelMapper().map(this, Endereco.class);
	}

	public EnderecoReqDTO(String logradouro, String numero, String bairro, String cidade, String uF, String pais,
			String cep) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		UF = uF;
		this.pais = pais;
		this.cep = cep;
	}

	public EnderecoReqDTO() {
		super();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}	
}
