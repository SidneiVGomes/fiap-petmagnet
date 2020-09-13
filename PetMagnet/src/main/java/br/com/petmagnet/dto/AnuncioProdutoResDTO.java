package br.com.petmagnet.dto;
 
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.model.AnuncioProduto;

public class AnuncioProdutoResDTO {
	private Long idProduto;
	private String descricao;
	private Double preco;
	private String imagem;
	
	@JsonIgnore
	private List<AnuncioProduto> produtos;
	
	public AnuncioProdutoResDTO(AnuncioProduto obj) {
		this.setIdProduto(obj.getId());
		this.setDescricao(obj.getDescricao());
		this.setImagem(obj.getImagem());
		this.setPreco(obj.getPreco());
	}

	public AnuncioProdutoResDTO(List<AnuncioProduto> obj) {
		this.setProdutos(obj);
	}

	public AnuncioProdutoResDTO(Long idProduto, Long idAnuncio, String descricao, Double preco, String imagem) {
		super();
		this.setIdProduto(idProduto);
		this.setDescricao(descricao);
		this.setPreco(preco);
		this.setImagem(imagem);
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
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
}
