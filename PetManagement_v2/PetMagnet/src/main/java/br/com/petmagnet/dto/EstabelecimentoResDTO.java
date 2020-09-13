package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.model.Estabelecimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstabelecimentoResDTO {
	private Long Id;
	private String cnpj;
	private String nome;
	private String complEndereco;
	private EnderecoResDTO endereco;
	
	@JsonIgnore
	private List<Estabelecimento> estabelecimentos;
	
	public EstabelecimentoResDTO(Estabelecimento estabelecimento) {
		this.setId(estabelecimento.getId());
		this.setCnpj(estabelecimento.getCnpj());
		this.setNome(estabelecimento.getNome());
		this.setComplEndereco(estabelecimento.getComplEndereco());
		this.setEndereco(new EnderecoResDTO(estabelecimento.getEndereco()));
	}

	public EstabelecimentoResDTO(List<Estabelecimento> estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}
	
	public List<EstabelecimentoResDTO> toList () {
		List<EstabelecimentoResDTO> listDTO = new ArrayList<EstabelecimentoResDTO>();
		
		for (Estabelecimento e : this.estabelecimentos) {
			listDTO.add(new EstabelecimentoResDTO(e));
		}
		
		return listDTO;
	}

	public EstabelecimentoResDTO(Long id, String cnpj, String nome, String complEndereco, EnderecoResDTO endereco,
			List<Estabelecimento> estabelecimentos) {
		super();
		Id = id;
		this.cnpj = cnpj;
		this.nome = nome;
		this.complEndereco = complEndereco;
		this.endereco = endereco;
		this.estabelecimentos = estabelecimentos;
	}

	public EstabelecimentoResDTO() {
		super();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

	public List<Estabelecimento> getEstabelecimentos() {
		return estabelecimentos;
	}

	public void setEstabelecimentos(List<Estabelecimento> estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}
}
