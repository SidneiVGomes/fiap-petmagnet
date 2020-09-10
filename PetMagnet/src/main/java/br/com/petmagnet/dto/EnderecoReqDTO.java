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
}
