package br.com.petmagnet.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import groovy.transform.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tb_anuncio")
public class Anuncio extends LogRegistro{
	private static final long serialVersionUID = 1L;
 
	@Id
	@Column(name = "id_anuncio")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_anuncio")
	private Long id;
	
	@Column(name = "ds_accesskey", length = 30)
	@JsonIgnore
	private String accessKey;
	
	@Column(name = "ds_titulo", length = 60)
	@NotEmpty	
	private String titulo;

	@Column(name = "ds_descricao", length = 200)
	@NotEmpty	
	private String descricao;

	@OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AnuncioProduto> produtos;
	
	@ManyToOne()
	@JoinColumn(name = "id_estabelecimento")
	private Estabelecimento estabelecimento;

	@ManyToOne()
	@JoinColumn(name = "id_colaborador")
	private Colaborador colaborador;
 
    @ManyToMany(mappedBy="anuncios")
	private List<Publicacao> publicacoes;

	public Anuncio(Long id, @NotEmpty String titulo, @NotEmpty String descricao, List<AnuncioProduto> produtos,
			Estabelecimento estabelecimento, Colaborador colaborador, List<Publicacao> publicacoes) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produtos = produtos;
		this.estabelecimento = estabelecimento;
		this.colaborador = colaborador;
		this.publicacoes = publicacoes;
		this.accessKey = Long.toHexString(estabelecimento.getId()) + 
				         Long.toHexString(colaborador.getId())+
				         Instant.now().toString().replaceAll("-", "").replaceAll(":", "");
		
	}

	public Anuncio() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
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

	public List<AnuncioProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<AnuncioProduto> produtos) {
		this.produtos = produtos;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}

}
