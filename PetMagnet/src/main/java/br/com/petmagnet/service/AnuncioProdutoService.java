package br.com.petmagnet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.petmagnet.dto.AnuncioProdutoReqDTO;
import br.com.petmagnet.model.AnuncioProduto;

@Service
public interface AnuncioProdutoService {
	public AnuncioProduto cadastrar(AnuncioProdutoReqDTO obj);
	public AnuncioProduto alterar(Long id, AnuncioProdutoReqDTO obj);
	public AnuncioProduto excluir(Long id);
	public AnuncioProduto consultarPorId(Long id);
	public List<AnuncioProduto> consultarTodos();
}
