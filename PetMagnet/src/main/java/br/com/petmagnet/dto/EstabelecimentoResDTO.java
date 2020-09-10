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
}
