package br.com.petmagnet.dto;

import java.util.List;

import br.com.petmagnet.model.AnuncioProduto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnuncioResponseDTO {
	private Long id;
	private String titulo;
	private String descricao;
	private List<AnuncioProduto> produtos;
}
