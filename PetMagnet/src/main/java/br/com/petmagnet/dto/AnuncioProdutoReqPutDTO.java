package br.com.petmagnet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.AnuncioProduto;
import br.com.petmagnet.model.Estabelecimento;

public class AnuncioProdutoReqPutDTO implements RequestDTO<AnuncioProduto> {
	@JsonIgnore
	private Long idEstabelecimento;
	@JsonIgnore
	private Long idAnuncio;
	@JsonIgnore
	private Long idProduto;
	private String descricao;
	private Double preco;
	private String imagem;

	@Override
	public AnuncioProduto toEntity() {
		Estabelecimento estabelecimento = new Estabelecimento();
		Anuncio anuncio = new Anuncio();
		AnuncioProduto produto = new AnuncioProduto();
		
		estabelecimento.setId(this.idEstabelecimento);
		
		anuncio.setId(this.idAnuncio);
		anuncio.setEstabelecimento(estabelecimento);
		
		produto.setAnuncio(anuncio);
		produto.setDescricao(this.descricao);
		produto.setPreco(this.preco);
		produto.setImagem(this.imagem);
		
		return produto;
	}	
	
	public AnuncioProduto toEntity(Long idEstabelecimento, Long idAnuncio, Long idProduto) {
		this.setIdEstabelecimento(idEstabelecimento);
		this.setIdAnuncio(idAnuncio);
		this.setIdProduto(idProduto);
		
		return this.toEntity();
	}

	public AnuncioProdutoReqPutDTO(Long idEstabelecimento, Long idAnuncio, Long idProduto, String descricao,
			Double preco, String imagem) {
		super();
		this.setIdEstabelecimento(idEstabelecimento);
		this.setIdAnuncio(idAnuncio);
		this.setIdProduto(idProduto);
		this.setDescricao(descricao);
		this.setPreco(preco);
		this.setImagem(imagem);
	}

	public AnuncioProdutoReqPutDTO() {
		super();
	}

	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public Long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(Long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}	
}
