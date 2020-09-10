package br.com.petmagnet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.petmagnet.model.AnuncioProduto;

@Service
public interface AnuncioProdutoService {
	public AnuncioProduto cadastrar(AnuncioProduto obj);
	public AnuncioProduto alterar(Long idEstabelecimento, Long idAnuncio, Long idProduto, AnuncioProduto obj);
	public AnuncioProduto excluir(Long idProduto);
	public AnuncioProduto consultarPorId(Long idProduto);
	public List<AnuncioProduto> consultarTodos();
}
