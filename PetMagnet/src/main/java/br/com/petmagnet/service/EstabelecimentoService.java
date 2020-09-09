package br.com.petmagnet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.petmagnet.dto.EstabelecimentoResDTO;
import br.com.petmagnet.model.Estabelecimento;

@Service
public interface EstabelecimentoService {
	public Estabelecimento cadastrar(Estabelecimento obj);
	public Estabelecimento alterar(Long id, Estabelecimento obj);
	public Estabelecimento excluir(Long id);
	public Estabelecimento consultarPorId(Long id);
	public List<Estabelecimento> consultarTodos();
//	public EstabelecimentoResponseDTO convertToDTO(Estabelecimento obj);
}
