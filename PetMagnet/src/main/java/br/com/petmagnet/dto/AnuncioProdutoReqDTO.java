package br.com.petmagnet.dto;
 
import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.AnuncioProduto;
 
public class AnuncioProdutoReqDTO implements RequestDTO<AnuncioProduto> {
	private Long id;
	private String descricao;
	private Double preco;
	private String imagem;

	public AnuncioProdutoReqDTO(Long id, String descricao, Double preco, String imagem) {
		super();
		this.setId(id);
		this.setDescricao(descricao);
		this.setPreco(preco);
		this.setImagem(imagem);
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@Override
	public AnuncioProduto toEntity() {
		AnuncioProduto produto = new AnuncioProduto();
		
		produto.setDescricao(this.descricao);
		produto.setPreco(this.preco);
		produto.setImagem(this.imagem);
		
		return produto;
	}

}
