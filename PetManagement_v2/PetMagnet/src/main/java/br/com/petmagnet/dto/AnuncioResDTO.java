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

	public AnuncioResDTO(String accessKey, Long idAnuncio, Long idEstabelecimento, Long idColaborador, String titulo,
			String descricao, List<AnuncioProduto> produtos, List<Anuncio> anuncios) {
		super();
		this.accessKey = accessKey;
		this.idAnuncio = idAnuncio;
		this.idEstabelecimento = idEstabelecimento;
		this.idColaborador = idColaborador;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produtos = produtos;
		this.anuncios = anuncios;
	}

	public AnuncioResDTO() {
		super();
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public Long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(Long idAnuncio) {
		this.idAnuncio = idAnuncio;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<AnuncioProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<AnuncioProduto> produtos) {
		this.produtos = produtos;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
}
