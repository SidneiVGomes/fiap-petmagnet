package br.com.petmagnet.dto;
 
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.AnuncioProduto;
 
public class AnuncioProdutoReqDTO implements RequestDTO<AnuncioProduto> {
	private Long id;
	private String descricao;
	private Double preco;
	@JsonIgnore
	private byte[] imagem_byte;

	public AnuncioProdutoReqDTO(Long id, String descricao, Double preco, byte[] imagem_byte) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.imagem_byte = imagem_byte;
	}

	public AnuncioProdutoReqDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public byte[] getImagem_byte() {
		return imagem_byte;
	}

	public void setImagem_byte(byte[] imagem_byte) {
		this.imagem_byte = imagem_byte;
	}

	@Override
	public AnuncioProduto toEntity() {
		AnuncioProduto produto = new AnuncioProduto();
		
		produto.setDescricao(this.descricao);
		produto.setPreco(this.preco);
		produto.setImagem_byte(this.imagem_byte);
		
		return produto;
	}
}
