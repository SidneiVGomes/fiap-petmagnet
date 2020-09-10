package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.petmagnet.model.AnuncioProduto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnuncioProdutoResDTO {
	private Long idEstabelecimento;
	private Long idAnuncio;
	private String descricao;
	private Double preco;
	private String imagem;
	private List<AnuncioProduto> produtos;
	
	public AnuncioProdutoResDTO(AnuncioProduto obj) {
		this.setDescricao(obj.getDescricao());
		this.setIdAnuncio(obj.getAnuncio().getId());
		this.setIdEstabelecimento(obj.getAnuncio().getEstabelecimento().getId());
		this.setImagem(obj.getImagem());
		this.setPreco(obj.getPreco());
		this.setProdutos(obj.getAnuncio().getProdutos());
	}

	public AnuncioProdutoResDTO(List<AnuncioProduto> obj) {
		this.setProdutos(obj);
	}

	public List<AnuncioProdutoResDTO> toList() {
		List<AnuncioProdutoResDTO> listDTO = new ArrayList<AnuncioProdutoResDTO>();
		
		for (AnuncioProduto e : this.produtos) {
			listDTO.add(new AnuncioProdutoResDTO(e));
		}
		
		return listDTO;
	}	
}
