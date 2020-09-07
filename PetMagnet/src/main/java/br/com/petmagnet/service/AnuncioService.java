package br.com.petmagnet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.petmagnet.dto.AnuncioDTO;
import br.com.petmagnet.dto.AnuncioResponseDTO;
import br.com.petmagnet.model.Anuncio;

@Service
public interface AnuncioService {
	public Anuncio cadastrar(AnuncioDTO obj);
	public Anuncio alterar(Long id, AnuncioDTO obj);
	public Anuncio excluir(Long idEstabelecimento, Long idAnuncio);
	public Optional<Anuncio> consultarPorId(Long idEstabelecimento, Long idAnuncio);
	public List<Anuncio> consultarTodos();
	public List<Anuncio> consultarPorColaborador(Long idEstabelecimento, Long idColaborador);
	public AnuncioResponseDTO convertParaDTO(Anuncio obj);
}