package br.com.petmagnet.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.petmagnet.model.Anuncio;

@Service
public interface AnuncioService {
	public Anuncio gravar(Anuncio obj);
	public Anuncio alterar(Anuncio obj);
	public Anuncio excluir(Long idEstabelecimento, Long idAnuncio, Long idProduto);
	public Optional<Anuncio> consultarPorId(Long idEstabelecimento, Long idAnuncio);
	public List<Anuncio> consultarTodos();
	public List<Anuncio> consultarPorColaborador(Long idEstabelecimento, Long idColaborador, Boolean publicado);
	public List<Anuncio> consultarPorEstabelecimento(Long idEstabelecimento, Boolean publicado);
	public Anuncio publicarDireto(Anuncio obj, Date dtPublicacao, Date dtEncerramento);
}
