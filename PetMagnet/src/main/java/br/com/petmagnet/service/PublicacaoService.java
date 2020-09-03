package br.com.petmagnet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.petmagnet.dto.PublicacaoDTO;
import br.com.petmagnet.dto.PublicacaoResponseAtivaDTO;
import br.com.petmagnet.model.Publicacao;

@Service
public interface PublicacaoService {
	public Publicacao publicar(PublicacaoDTO obj);
	public Publicacao alterar(Long id, PublicacaoDTO obj);
	public Publicacao excluir(Long idEstabelecimento, Long idPublicacao);
	public Optional<Publicacao> consultarPorId(Long idEstabelecimento, Long idPublicacao);
	public List<Publicacao> consultarTodos();
	public List<Publicacao> localizarPublicacoesAtivas();
	public PublicacaoResponseAtivaDTO convertParaDTO(Publicacao obj);
}
