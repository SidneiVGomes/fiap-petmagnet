package br.com.petmagnet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_anuncio_produto")
public class AnuncioProduto extends LogRegistro{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_anuncio_produto")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_anuncio_produto")
	private Long id;

	@Column(name = "ds_produto", length = 60)
	@NotEmpty	
	private String descricao;

	@Column(name = "vl_anunciado", length = 20)
	@Min(1)	
	private Double preco;

	@Column(name = "ds_imagem")
//	@NotEmpty	
//	private Blob imagem;
	private String imagem;
	
	@ManyToOne()
	@JoinColumn(name = "id_anuncio")
	private Anuncio anuncio;

	public AnuncioProduto(Long id, @NotEmpty String descricao, @Min(1) Double preco, String imagem, Anuncio anuncio) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.imagem = imagem;
		this.anuncio = anuncio;
	}

	public AnuncioProduto() {
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

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}	
}
