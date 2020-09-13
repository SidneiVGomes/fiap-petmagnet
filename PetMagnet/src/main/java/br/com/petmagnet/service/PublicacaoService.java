package br.com.petmagnet.service;
   
import java.util.List;
import java.util.Optional;
 
import org.springframework.stereotype.Service;

import br.com.petmagnet.model.Publicacao;

@Service
public interface PublicacaoService {
	public Publicacao publicar(Publicacao obj);
	public Publicacao alterar(Long id, Publicacao obj);
	public Publicacao excluir(Long idEstabelecimento, Long idPublicacao);
	public Publicacao cancelar(Long idEstabelecimento, Long idPublicacao);
	public Optional<Publicacao> consultarPorId(Long idEstabelecimento, Long idPublicacao);
	public List<Publicacao> consultarTodos(Long idEstabelecimento, Boolean exibirEncerrados);
	public List<Publicacao> localizarPublicacoesProximas(Long latitude, Long longitude);
}
