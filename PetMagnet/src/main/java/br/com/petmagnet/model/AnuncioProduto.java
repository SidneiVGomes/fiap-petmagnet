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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
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
	@JsonIgnore
	private Anuncio anuncio;
}
