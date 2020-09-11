package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.AnuncioProduto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnuncioResDTO {
	@JsonIgnore
	private String accessKey;
	private Long idAnuncio;
	private Long idEstabelecimento;
	private Long idColaborador;
	private String titulo;
	private String descricao;
	private List<AnuncioProduto> produtos;
	
	@JsonIgnore
	private List<Anuncio> anuncios;
	
	public AnuncioResDTO(Anuncio obj) {
		this.setIdEstabelecimento(obj.getEstabelecimento().getId());
		this.setIdColaborador(obj.getColaborador().getId());
		this.setIdAnuncio(obj.getId());
		this.setTitulo(obj.getTitulo());
		this.setDescricao(obj.getDescricao());
		this.setProdutos(obj.getProdutos());
		this.setAccessKey(obj.getAccessKey());
	}

	public AnuncioResDTO(List<Anuncio> obj) {
		this.setAnuncios(obj);
	}

	public List<AnuncioResDTO> toList() {
		List<AnuncioResDTO> listDTO = new ArrayList<AnuncioResDTO>();
		
		for (Anuncio e : this.anuncios) {
			listDTO.add(new AnuncioResDTO(e));
		}
		
		return listDTO;
	}
}
