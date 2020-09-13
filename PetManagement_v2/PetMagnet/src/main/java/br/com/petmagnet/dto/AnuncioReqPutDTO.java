package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.AnuncioProduto;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnuncioReqPutDTO implements RequestDTO{
	@JsonIgnore
	private Long idEstabelecimento;
	@JsonIgnore
	private Long idColaborador;
	@JsonIgnore
	private Long idAnuncio;
	private String titulo;
	private String descricao;
	private List<AnuncioProdutoReqDTO> produtos;
	
	@Override
	public Object toEntity() {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setId(this.idEstabelecimento);

		Colaborador colaborador = new Colaborador();
		colaborador.setId(this.idColaborador);
		
		Anuncio anuncio = new Anuncio();
		
		anuncio.setId(this.idAnuncio);
		anuncio.setColaborador(colaborador);
		anuncio.setEstabelecimento(estabelecimento);
		anuncio.setTitulo(this.titulo);
		anuncio.setDescricao(this.descricao);

		List<AnuncioProduto> anuncioProdutos = new ArrayList<AnuncioProduto>();

		for (AnuncioProdutoReqDTO produtoDTO : produtos) {
			AnuncioProduto anuncioProduto = new AnuncioProduto();
			
			anuncioProduto.setId(produtoDTO.getId());
			anuncioProduto.setAnuncio(anuncio);
			anuncioProduto.setDescricao(produtoDTO.getDescricao());
			anuncioProduto.setPreco(produtoDTO.getPreco());
			anuncioProduto.setImagem(produtoDTO.getImagem());
			
			anuncioProdutos.add(anuncioProduto);
		}
		
		anuncio.setProdutos(anuncioProdutos);

		return anuncio;
	}
	
	public Object toEntity(Long idEstabelecimento, Long idColaborador, Long idAnuncio) {
		this.setIdEstabelecimento(idEstabelecimento);
		this.setIdAnuncio(idAnuncio);
		this.setIdColaborador(idColaborador);
		
		return this.toEntity();
	}

	public AnuncioReqPutDTO(Long idEstabelecimento, Long idColaborador, Long idAnuncio, String titulo, String descricao,
			List<AnuncioProdutoReqDTO> produtos) {
		super();
		this.idEstabelecimento = idEstabelecimento;
		this.idColaborador = idColaborador;
		this.idAnuncio = idAnuncio;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produtos = produtos;
	}

	public AnuncioReqPutDTO() {
		super();
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

	public List<AnuncioProdutoReqDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<AnuncioProdutoReqDTO> produtos) {
		this.produtos = produtos;
	}
}