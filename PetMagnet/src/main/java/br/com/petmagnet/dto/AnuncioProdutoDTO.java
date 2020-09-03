package br.com.petmagnet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnuncioProdutoDTO {
	private Long idEstabelecimento;
	private Long idAnuncio;
	private String descricao;
	private Double preco;
	private String imagem;
}
