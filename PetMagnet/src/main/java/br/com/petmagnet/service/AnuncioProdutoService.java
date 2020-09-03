package br.com.petmagnet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.petmagnet.dto.AnuncioProdutoDTO;
import br.com.petmagnet.model.AnuncioProduto;

@Service
public interface AnuncioProdutoService {
	public AnuncioProduto cadastrar(AnuncioProdutoDTO obj);
	public AnuncioProduto alterar(Long id, AnuncioProdutoDTO obj);
	public AnuncioProduto excluir(Long id);
	public AnuncioProduto consultarPorId(Long id);
	public List<AnuncioProduto> consultarTodos();
}
