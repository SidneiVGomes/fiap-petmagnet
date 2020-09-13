package br.com.petmagnet.dto;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.AnuncioProduto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnuncioProdutoReqDTO implements RequestDTO{
	private Long id;
	private String descricao;
	private Double preco;
	private String imagem;

	public AnuncioProdutoReqDTO() {
		super();
	}

	public AnuncioProdutoReqDTO(Long id, String descricao, Double preco, String imagem) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.imagem = imagem;
	}
	
	@Override
	public Object toEntity() {
//		Estabelecimento estabelecimento = new Estabelecimento();
//		Anuncio anuncio = new Anuncio();
		AnuncioProduto produto = new AnuncioProduto();
		
		produto.setDescricao(this.descricao);
		produto.setPreco(this.preco);
		produto.setImagem(this.imagem);
		
		return produto;
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}		
}
