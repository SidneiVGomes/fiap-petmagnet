package br.com.petmagnet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.AnuncioProduto;
import br.com.petmagnet.model.Estabelecimento;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnuncioProdutoReqPutDTO implements RequestDTO{
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
	public Object toEntity() {
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
	
	public Object toEntity(Long idEstabelecimento, Long idAnuncio, Long idProduto) {
		this.setIdEstabelecimento(idEstabelecimento);
		this.setIdAnuncio(idAnuncio);
		this.setIdProduto(idProduto);
		
		return this.toEntity();
	}	
}
