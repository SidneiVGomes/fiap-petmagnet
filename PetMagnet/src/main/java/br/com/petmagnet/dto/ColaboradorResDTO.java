package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.model.Colaborador;

public class ColaboradorResDTO {
	private Long idColaborador;	
	private String nome;
	private Long idEstabelecimento;

	@JsonIgnore
	private List<Colaborador> colaboradores;
	
	public ColaboradorResDTO(Colaborador obj) {
		this.setIdColaborador(obj.getId());
		this.setNome(obj.getNome());
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

	public ColaboradorResDTO(Long id, String nome, String senha, Long idEstabelecimento) {
		super();
		this.setIdColaborador(idColaborador);
		this.setNome(nome);
		this.setIdEstabelecimento(idEstabelecimento);
	}

	public ColaboradorResDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public Long getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(Long idColaborador) {
		this.idColaborador = idColaborador;
	}
}
