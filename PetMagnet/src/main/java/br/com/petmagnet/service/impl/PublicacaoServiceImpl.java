package br.com.petmagnet.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.exception.AppBeanNotFoundException;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.model.Publicacao;
import br.com.petmagnet.model.Usuario;
import br.com.petmagnet.repository.PublicacaoRepository;
import br.com.petmagnet.service.PublicacaoService;
import groovy.util.logging.Slf4j;

@Slf4j
@Service
@Transactional
public class PublicacaoServiceImpl implements PublicacaoService {
	@Autowired
	private PublicacaoRepository publicacaoRepository;

	@Autowired
	private EstabelecimentoServiceImpl estabelecimentoService;

	@Autowired
	private ColaboradorServiceImpl colaboradorService;

	@Autowired
	private AnuncioServiceImpl anuncioService;
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
		
	@Override
	public Publicacao publicar(Publicacao obj) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(obj.getEstabelecimento().getId());

		Colaborador colaborador = this.colaboradorService
				.consultarPorColaborador(estabelecimento, obj.getColaborador().getId()).get();

		List<Anuncio> anuncios = new ArrayList<Anuncio>();

		obj.getAnuncios().stream().forEach(anuncio -> {
			Anuncio a = this.anuncioService.consultarPorId(obj.getEstabelecimento().getId(), anuncio.getId()).get();

			if (this.publicacaoRepository.findByEstabelecimentoAndAnunciosAndCancelado(estabelecimento, a,
					false) != null) {
				throw new AppBeanNotFoundException("O anúncio " + a.getId() + " já foi publicado");
			}
			;

			anuncios.add(a);
		});

		Publicacao publicacao = this.publicacaoRepository.save(new Publicacao(null, obj.getDtPublicacao(),
				obj.getDtEncerramento(), estabelecimento, colaborador, anuncios, Boolean.FALSE));

		return publicacao;
	}

	@Override
	public Publicacao excluir(Long idEstabelecimento, Long idPublicacao) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento);

		Publicacao publicacao = this.publicacaoRepository.findByEstabelecimentoAndId(estabelecimento, idPublicacao)
				.orElseThrow(() -> new AppBeanNotFoundException("Publicação não Cadastrada"));

		this.publicacaoRepository.deleteById(idPublicacao);
		return publicacao;
	}

	@Override
	public List<Publicacao> consultarTodos(Long idEstabelecimento, Boolean exibirEncerrados) {
		List<Publicacao> publicacoes = new ArrayList<Publicacao>();

		this.publicacaoRepository.findAll().stream().forEach(publicacao -> {

			if (publicacao.getCancelado()) {
				// Não exibe o anúncio

			} else if (!exibirEncerrados) {
				// Exibe somente os anúncios publicados que não foram encerrados ou os que ainda
				// não foram publicados.
				if (!this.pubicacaoEncerrada(publicacao)) {
					publicacoes.add(publicacao);
				}

			} else if (exibirEncerrados) {
				// Exibr somente os anúncios que já foram publicados e que já foram encerrados.
				if (this.pubicacaoEncerrada(publicacao)) {
					publicacoes.add(publicacao);
				}

			}
		});

		return publicacoes;
	}

	@Override
	public Optional<Publicacao> consultarPorId(Long idEstabelecimento, Long idPublicacao) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento);

		Publicacao publicacao = this.publicacaoRepository.findByEstabelecimentoAndId(estabelecimento, idPublicacao)
				.orElseThrow(() -> new AppBeanNotFoundException("Publicação não Cadastrada"));

		return Optional.ofNullable(publicacao);
	}

	@Override
	public Publicacao alterar(Long id, Publicacao obj) {
		return null;
	}

	@Override
	public Publicacao cancelar(Long idEstabelecimento, Long idPublicacao) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento);

		Publicacao publicacao = this.publicacaoRepository.findByEstabelecimentoAndId(estabelecimento, idPublicacao)
				.get();

		publicacao.setCancelado(Boolean.TRUE);

		return this.publicacaoRepository.save(publicacao);
	}

	@Override
	public List<Publicacao> localizarPublicacoesProximas(Long idUsuario, Integer alcanceKM) {
		if (alcanceKM == 0) {
			alcanceKM = 2;
		}
		
		return this.publicacaoRepository.findByPublicacoesProximas(idUsuario, (alcanceKM * 1000));
	}

	public Boolean pubicacaoEncerrada(Publicacao publicacao) {
		Instant dtPublicacao = publicacao.getDtPublicacao().toInstant();
		Instant dtEncerramento = publicacao.getDtEncerramento().toInstant();

		if (dtPublicacao.compareTo(Instant.now()) <= 0 && Instant.now().compareTo(dtEncerramento) <= 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Publicacao publicarSimples(Publicacao obj) {
		obj.getAnuncios().stream().forEach(anuncio -> {
			Anuncio a = this.anuncioService.consultarPorId(obj.getEstabelecimento().getId(), anuncio.getId()).get();

			if (this.publicacaoRepository.findByEstabelecimentoAndAnunciosAndCancelado(obj.getEstabelecimento(), a,
					false) != null) {
				throw new AppBeanNotFoundException("O anúncio " + a.getId() + " já foi publicado");
			}
			;
		});

		return this.publicacaoRepository.save(obj);
	}
}
