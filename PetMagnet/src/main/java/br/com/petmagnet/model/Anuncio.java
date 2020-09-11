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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
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
	@JsonIgnore
	private Estabelecimento estabelecimento;

	@ManyToOne()
	@JoinColumn(name = "id_colaborador")
	@JsonIgnore
	private Colaborador colaborador;
 
    @ManyToMany(mappedBy="anuncios")
    @JsonIgnore
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
}
