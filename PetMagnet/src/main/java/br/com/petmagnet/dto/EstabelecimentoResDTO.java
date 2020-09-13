package br.com.petmagnet.dto;
 
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.model.Endereco;
import br.com.petmagnet.model.Estabelecimento;
 
public class EstabelecimentoResDTO {
	private Long IdEstabelecimento;
	private String cnpj;
	private String nome;
	private String complEndereco;
	private EnderecoResDTO endereco;
	
	@JsonIgnore
	private List<Estabelecimento> estabelecimentos;
	
	public EstabelecimentoResDTO(Estabelecimento estabelecimento) {
		this.setIdEstabelecimento(estabelecimento.getId());
		this.setCnpj(estabelecimento.getCnpj());
		this.setNome(estabelecimento.getNome());
		this.setComplEndereco(estabelecimento.getComplEndereco());
		this.setEndereco(new EnderecoResDTO(estabelecimento.getEndereco()));
	}

	public EstabelecimentoResDTO(List<Estabelecimento> estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}
		
	public EstabelecimentoResDTO(Long idEstabelecimento, String cnpj, String nome, String complEndereco, Endereco endereco) {
		super();
		this.setIdEstabelecimento(idEstabelecimento);
		this.setCnpj(cnpj);
		this.setNome(nome);
		this.setComplEndereco(complEndereco);
		this.setEndereco(new EnderecoResDTO(endereco));
	}
	
	public List<EstabelecimentoResDTO> toList () {
		List<EstabelecimentoResDTO> listDTO = new ArrayList<EstabelecimentoResDTO>();
		
		for (Estabelecimento e : this.estabelecimentos) {
			listDTO.add(new EstabelecimentoResDTO(e));
		}
		
		return listDTO;
	}

	public Long getIdEstabelecimento() {
		return IdEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		IdEstabelecimento = idEstabelecimento;
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

	public EnderecoResDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoResDTO endereco) {
		this.endereco = endereco;
	}
}
