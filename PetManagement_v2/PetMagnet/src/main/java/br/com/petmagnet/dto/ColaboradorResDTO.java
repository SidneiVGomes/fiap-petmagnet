package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.model.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorResDTO {
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

	public List<ColaboradorResDTO> toList() {
		List<ColaboradorResDTO> listDTO = new ArrayList<ColaboradorResDTO>();
		
		for (Colaborador e : this.colaboradores) {
			listDTO.add(new ColaboradorResDTO(e));
		}
		
		return listDTO;
	}

	public ColaboradorResDTO(Long id, String nome, String senha, Long idEstabelecimento,
			List<Colaborador> colaboradores) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.idEstabelecimento = idEstabelecimento;
		this.colaboradores = colaboradores;
	}

	public ColaboradorResDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}
}
