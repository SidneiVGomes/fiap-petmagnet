package br.com.petmagnet.dto;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorReqDTO implements RequestDTO {
	@Length(min = 3, message = "O Nome deve ter no mínimo 3 caracteres")
	private String nome;
	private String senha;
	private Long idEstabelecimento;
	
	@Override
	public Object toEntity() {
		return new ModelMapper().map(this, Colaborador.class);
	}
}
