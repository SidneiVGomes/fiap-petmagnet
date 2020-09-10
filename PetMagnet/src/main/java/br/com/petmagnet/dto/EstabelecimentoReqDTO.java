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
}
