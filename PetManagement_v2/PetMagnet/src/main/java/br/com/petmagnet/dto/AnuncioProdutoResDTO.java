package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.petmagnet.model.AnuncioProduto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnuncioProdutoResDTO {
	private Long idEstabelecimento;
	private Long idAnuncio;
	private String descricao;
	private Double preco;
	private String imagem;
	private List<AnuncioProduto> produtos;
	
	public AnuncioProdutoResDTO(AnuncioProduto obj) {
		this.setDescricao(obj.getDescricao());
		this.setIdAnuncio(obj.getAnuncio().getId());
		this.setIdEstabelecimento(obj.getAnuncio().getEstabelecimento().getId());
		this.setImagem(obj.getImagem());
		this.setPreco(obj.getPreco());
		this.setProdutos(obj.getAnuncio().getProdutos());
	}

	public AnuncioProdutoResDTO(List<AnuncioProduto> obj) {
		this.setProdutos(obj);
	}

	public List<AnuncioProdutoResDTO> toList() {
		List<AnuncioProdutoResDTO> listDTO = new ArrayList<AnuncioProdutoResDTO>();
		
		for (AnuncioProduto e : this.produtos) {
			listDTO.add(new AnuncioProdutoResDTO(e));
		}
		
		return listDTO;
	}

	public AnuncioProdutoResDTO(Long idEstabelecimento, Long idAnuncio, String descricao, Double preco, String imagem,
			List<AnuncioProduto> produtos) {
		super();
		this.idEstabelecimento = idEstabelecimento;
		this.idAnuncio = idAnuncio;
		this.descricao = descricao;
		this.preco = preco;
		this.imagem = imagem;
		this.produtos = produtos;
	}

	public AnuncioProdutoResDTO() {
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
}
