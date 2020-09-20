package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.AnuncioProduto;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;

public class AnuncioReqPublicacaoDTO implements RequestDTO<Anuncio> {
	private Long idEstabelecimento;
	private Long idColaborador;
	private String titulo;
	private String descricao;
	private Date dtPublicacao;
	private Date dtEncerramento;
	private List<AnuncioProdutoReqDTO> produtos;
	
	@Override
	public Anuncio toEntity() {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setId(this.idEstabelecimento);

		Colaborador colaborador = new Colaborador();
		colaborador.setId(this.idColaborador);
		
		Anuncio anuncio = new Anuncio();

		anuncio.setColaborador(colaborador);
		anuncio.setEstabelecimento(estabelecimento);
		anuncio.setTitulo(this.titulo);
		anuncio.setDescricao(this.descricao);

		AnuncioProduto anuncioProduto = new AnuncioProduto();
		List<AnuncioProduto> anuncioProdutos = new ArrayList<AnuncioProduto>();

		for (AnuncioProdutoReqDTO produtoDTO : produtos) {
			anuncioProduto.setAnuncio(anuncio);
			anuncioProduto.setDescricao(produtoDTO.getDescricao());
			anuncioProduto.setPreco(produtoDTO.getPreco());
			anuncioProduto.setImagem_byte(produtoDTO.getImagem_byte());
			
			anuncioProdutos.add(anuncioProduto);
		}
		
		anuncio.setProdutos(anuncioProdutos);

		return anuncio;
	}

	public AnuncioReqPublicacaoDTO(Long idEstabelecimento, Long idColaborador, String titulo, String descricao,
			Date dtPublicacao, Date dtEncerramento, List<AnuncioProdutoReqDTO> produtos) {
		super();
		this.idEstabelecimento = idEstabelecimento;
		this.idColaborador = idColaborador;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dtPublicacao = dtPublicacao;
		this.dtEncerramento = dtEncerramento;
		this.produtos = produtos;
	}

	public AnuncioReqPublicacaoDTO() {
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

	public Date getDtPublicacao() {
		return dtPublicacao;
	}

	public void setDtPublicacao(Date dtPublicacao) {
		this.dtPublicacao = dtPublicacao;
	}

	public Date getDtEncerramento() {
		return dtEncerramento;
	}

	public void setDtEncerramento(Date dtEncerramento) {
		this.dtEncerramento = dtEncerramento;
	}
}
