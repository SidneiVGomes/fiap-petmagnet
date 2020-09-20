package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.model.AnuncioProduto;
import br.com.petmagnet.util.AppImageCompress;

public class AnuncioProdutoResDTO {
	private Long idProduto;
	private String descricao;
	private Double preco;
	private byte[] imagem_byte;
	
	@JsonIgnore
	private List<AnuncioProduto> produtos;
	
	public AnuncioProdutoResDTO(AnuncioProduto obj) {
		this.setIdProduto(obj.getId());
		this.setDescricao(obj.getDescricao());
		this.setPreco(obj.getPreco());
		
		try {
			this.setImagem_byte(AppImageCompress.decompressBytes(obj.getImagem_byte()));
			
		} catch (Exception e) { }
	}

	public AnuncioProdutoResDTO(List<AnuncioProduto> obj) {
		this.setProdutos(obj);
	}

	public AnuncioProdutoResDTO(Long idProduto, String descricao, Double preco, String imagem, byte[] imagem_byte,
			List<AnuncioProduto> produtos) {
		super();
		this.idProduto = idProduto;
		this.descricao = descricao;
		this.preco = preco;
		this.produtos = produtos;
		
		try {
			this.imagem_byte = AppImageCompress.decompressBytes(imagem_byte);
		} catch (Exception e) { }
	}

	public List<AnuncioProdutoResDTO> toList() {
		List<AnuncioProdutoResDTO> listDTO = new ArrayList<AnuncioProdutoResDTO>();
		
		for (AnuncioProduto p : this.produtos) {
			listDTO.add(new AnuncioProdutoResDTO(p));
		}
		
		return listDTO;
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

	public List<AnuncioProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<AnuncioProduto> produtos) {
		this.produtos = produtos;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public byte[] getImagem_byte() {
		return imagem_byte;
	}

	public void setImagem_byte(byte[] imagem_byte) {
		this.imagem_byte = imagem_byte;
	}
}
