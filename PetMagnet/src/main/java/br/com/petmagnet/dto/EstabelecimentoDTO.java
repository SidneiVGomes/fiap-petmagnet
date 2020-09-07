package br.com.petmagnet.dto;

import br.com.petmagnet.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class EstabelecimentoDTO {
	private String cnpj;
	private String nome;
	private String complEndereco;
	private EnderecoDTO endereco;	
}
