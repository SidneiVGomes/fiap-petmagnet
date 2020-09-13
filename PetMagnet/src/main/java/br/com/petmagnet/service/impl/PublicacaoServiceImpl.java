package br.com.petmagnet.service.impl;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.exception.AppBeanNotFoundException;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.model.Publicacao;
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

	@Override
	public Publicacao publicar(Publicacao obj) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(obj.getEstabelecimento().getId());

		Colaborador colaborador = this.colaboradorService
				.consultarPorColaborador(estabelecimento, obj.getColaborador().getId()).get();

		obj.getAnuncios().stream().forEach(anuncio -> {
			this.anuncioService.consultarPorId(obj.getEstabelecimento().getId(), anuncio.getId());
		});

		Publicacao publicacao = this.publicacaoRepository.save(new Publicacao(null, obj.getDtPublicacao(),
				obj.getDtEncerramento(), estabelecimento, colaborador, obj.getAnuncios(), Boolean.FALSE));

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
		
		this.publicacaoRepository.findAll().stream()
			.forEach(publicacao -> {

				if (!publicacao.getCancelado()) {
					// Não exibe o anúncio
					
				} else if (!exibirEncerrados) {
					// Exibe somente os anúncios publicados que não foram encerrados ou os que ainda
					// não foram publicados.
					if ( !this.pubicacaoEncerrada(publicacao) ) {
						publicacoes.add(publicacao);
					}
										
				} else if (exibirEncerrados) {
					// Exibr somente os anúncios que já foram publicados e que já foram encerrados.
					if ( this.pubicacaoEncerrada(publicacao) ) {
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
		
		Publicacao publicacao = this.publicacaoRepository.findByEstabelecimentoAndId(estabelecimento, idPublicacao).get();
		
		publicacao.setCancelado(Boolean.TRUE);

		return this.publicacaoRepository.save(publicacao);
	}

	@Override
	public List<Publicacao> localizarPublicacoesProximas(Long latitude, Long longitude) {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		Date dtAtual = null;

		try {
			dtAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agora.format(formatador));
		} catch (ParseException e) {
		}

		List<Publicacao> publicacoes = this.publicacaoRepository.findByPublicacoesAtivas(dtAtual);

		return publicacoes;
	}
	
	public Boolean pubicacaoEncerrada(Publicacao publicacao) {
		Instant dtPublicacao = publicacao.getDtPublicacao().toInstant();
		Instant dtEncerramento = publicacao.getDtEncerramento().toInstant();
		
		if (dtPublicacao.compareTo(Instant.now()) <= 0 && Instant.now().compareTo(dtEncerramento) > 0 ) {
			return false;
		} else {
			return true;
		}
	}
}
