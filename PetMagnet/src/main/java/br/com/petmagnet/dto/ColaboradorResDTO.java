package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.dto.interfac.ResponseDTO;
import br.com.petmagnet.model.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorResDTO implements ResponseDTO {
	private Long id;	
	private String nome;
	private String senha;
	private Long idEstabelecimento;

	@JsonIgnore
	private List<Colaborador> colaboradores;
	
	public ColaboradorResDTO(Colaborador obj) {
		this.setId(obj.getId());
		this.setNome(obj.getNome());
		this.setSenha(obj.getSenha());
		this.setIdEstabelecimento(obj.getEstabelecimento().getId());
	}

	public ColaboradorResDTO(List<Colaborador> obj) {
		this.colaboradores = obj;
	}

	@Override
	public List<Object> toList() {
		List<Object> listDTO = new ArrayList<Object>();
		
		for (Colaborador e : this.colaboradores) {
			listDTO.add(new ColaboradorResDTO(e));
		}
		
		return listDTO;
	}
}
