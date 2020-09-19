package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.Publicacao;

public class AnuncioResPublicacaoDTO {
	@JsonIgnore
	private String accessKey;
	private Long idPublicacao;
	private Long idAnuncio;
	private Long idEstabelecimento;
	private Long idColaborador;
	private String titulo;
	private String descricao;
	private List<AnuncioProdutoResDTO> produtos;

	@JsonIgnore
	private List<Anuncio> anuncios;

	public AnuncioResPublicacaoDTO(Anuncio obj) {
		this.setIdEstabelecimento(obj.getEstabelecimento().getId());
		this.setIdColaborador(obj.getColaborador().getId());
		this.setIdAnuncio(obj.getId());
		this.setTitulo(obj.getTitulo());
		this.setDescricao(obj.getDescricao());
		this.setProdutos(new AnuncioProdutoResDTO(obj.getProdutos()).toList());
		this.setAccessKey(obj.getAccessKey());

		Publicacao publicacao = obj.getPublicacoes().get(obj.getPublicacoes().size()-1);
		
		this.setIdPublicacao(publicacao.getId());
	}

	public AnuncioResPublicacaoDTO(List<Anuncio> obj) {
		this.setAnuncios(obj);
	}

	public AnuncioResPublicacaoDTO(Long idPublicacao, Long idAnuncio, Long idEstabelecimento, Long idColaborador,
			String titulo, String descricao, List<AnuncioProdutoResDTO> produtos, List<Anuncio> anuncios) {
		super();
		this.idPublicacao = idPublicacao;
		this.idAnuncio = idAnuncio;
		this.idEstabelecimento = idEstabelecimento;
		this.idColaborador = idColaborador;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produtos = produtos;
		this.anuncios = anuncios;
	}

	public List<AnuncioResPublicacaoDTO> toList() {
		List<AnuncioResPublicacaoDTO> listDTO = new ArrayList<AnuncioResPublicacaoDTO>();

		for (Anuncio e : this.anuncios) {
			listDTO.add(new AnuncioResPublicacaoDTO(e));
		}

		return listDTO;
	}

	public AnuncioResPublicacaoDTO() {
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

	public List<AnuncioProdutoResDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<AnuncioProdutoResDTO> produtos) {
		this.produtos = produtos;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
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

	public Long getIdPublicacao() {
		return idPublicacao;
	}

	public void setIdPublicacao(Long idPublicacao) {
		this.idPublicacao = idPublicacao;
	}
}
