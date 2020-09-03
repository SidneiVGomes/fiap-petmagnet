package br.com.petmagnet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnuncioDTO {
	private Long idEstabelecimento;
	private Long idColaborador;
	private String titulo;
	private String descricao;
}
