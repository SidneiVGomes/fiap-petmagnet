package br.com.petmagnet.dto;
 
import org.modelmapper.ModelMapper;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Endereco;
import br.com.petmagnet.model.Estabelecimento;
 
public class EstabelecimentoReqDTO implements RequestDTO<Estabelecimento> {
	private String cnpj;
	private String nome;
	private String complEndereco;
	private EnderecoReqDTO endereco;

	public Estabelecimento toEntity() {
		return new ModelMapper().map(this, Estabelecimento.class);
	}

	public EstabelecimentoReqDTO(String cnpj, String nome, String complEndereco, Endereco endereco) {
		super();
		this.setCnpj(cnpj);
		this.setNome(nome);
		this.setComplEndereco(complEndereco);
		this.setEndereco(new EnderecoReqDTO(endereco));
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
