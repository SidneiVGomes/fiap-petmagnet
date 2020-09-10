package br.com.petmagnet.dto;

import org.modelmapper.ModelMapper;

import br.com.petmagnet.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResDTO {
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String UF;
	private String pais;
	private String cep;

	public EnderecoResDTO(Endereco endereco) {
		this.setLogradouro(endereco.getLogradouro());
		this.setNumero(endereco.getNumero());
		this.setBairro(endereco.getBairro());
		this.setCidade(endereco.getCidade());
		this.setUF(endereco.getUF());
		this.setPais(endereco.getPais());
		this.setCep(endereco.getCep());
	}	
}
