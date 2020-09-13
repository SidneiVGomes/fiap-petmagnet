package br.com.petmagnet.service;

import java.util.List;
 
import org.springframework.stereotype.Service;

import br.com.petmagnet.model.AnuncioProduto;

@Service
public interface AnuncioProdutoService {
	public AnuncioProduto gravar(AnuncioProduto obj);
	public List<AnuncioProduto> gravarTodos(List<AnuncioProduto> obj);
	public AnuncioProduto alterar(Long idEstabelecimento, Long idAnuncio, Long idProduto, AnuncioProduto obj);
	public AnuncioProduto excluir(Long idEstabelecimento, Long idAnuncio, Long idProduto);
	public AnuncioProduto consultarPorId(Long idAnuncio, Long idEstabelecimento, Long idProduto);
	public List<AnuncioProduto> consultarTodos();
}
