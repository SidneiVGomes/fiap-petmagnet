package br.com.petmagnet.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.dto.PublicacaoDTO;
import br.com.petmagnet.dto.PublicacaoResponseAtivaDTO;
import br.com.petmagnet.exception.BeanNotFoundException;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.model.Publicacao;
import br.com.petmagnet.repository.PublicacaoRepository;
import br.com.petmagnet.service.PublicacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
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
	public Publicacao publicar(PublicacaoDTO obj) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(obj.getIdEstabelecimento())
				.orElseThrow(() -> new BeanNotFoundException("Estabelecimento não Cadastrado"));
		
		Colaborador colaborador = this.colaboradorService.consultarPorColaborador(estabelecimento, obj.getIdColaborador())
				.orElseThrow(() -> new BeanNotFoundException("Colaborador não Cadastrado"));

		Anuncio anuncio = this.anuncioService.consultarPorId(obj.getIdEstabelecimento(), obj.getIdAnuncio())
				.orElseThrow(() -> new BeanNotFoundException("Anúncio não Cadastrado"));
		
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		
		anuncios.add(anuncio);
		
		Publicacao publicacao = this.publicacaoRepository.save(
				new Publicacao(null, obj.getDtPublicacao(), obj.getDtEncerramento(), estabelecimento, colaborador, anuncios)
			); 
		
		return publicacao;
	}
 
	@Override
	public Publicacao excluir(Long idEstabelecimento, Long idPublicacao) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento)
				.orElseThrow(() -> new BeanNotFoundException("Estabelecimento não Cadastrado"));

		Publicacao publicacao = this.publicacaoRepository.findByEstabelecimentoAndId(estabelecimento, idPublicacao)
				.orElseThrow(() -> new BeanNotFoundException("Publicação não Cadastrada"));
		
		this.publicacaoRepository.deleteById(idPublicacao);
		return publicacao;	
	}

	@Override
	public List<Publicacao> consultarTodos() {
		return null;
	}

	@Override
	public Optional<Publicacao> consultarPorId(Long idEstabelecimento, Long idPublicacao) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento)
				.orElseThrow(() -> new BeanNotFoundException("Estabelecimento não Cadastrado"));
		
		return this.publicacaoRepository.findByEstabelecimentoAndId(estabelecimento, idPublicacao);
	}

	@Override
	public Publicacao alterar(Long id, PublicacaoDTO publicacaoDTO) {
		Publicacao publicacao = this.publicacaoRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Publicacao não Cadastrada"));
		
		publicacao.setDtPublicacao(publicacaoDTO.getDtPublicacao());
		publicacao.setDtEncerramento(publicacaoDTO.getDtEncerramento());

		return this.publicacaoRepository.save(publicacao);
	}

	@Override
	public List<Publicacao> localizarPublicacoesAtivas() {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		Date dtAtual = null;
		
		try {
			dtAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agora.format(formatador));
		} catch (ParseException e) {
		} 
		
		List<Publicacao> publicacoes = this.publicacaoRepository.findByPublicacoesAtivas( dtAtual );
		
		return publicacoes;
	}

	@Override
	public PublicacaoResponseAtivaDTO convertParaDTO(Publicacao obj) {
		return new PublicacaoResponseAtivaDTO(obj.getId(), obj.getDtPublicacao(), obj.getDtEncerramento(), obj.getEstabelecimento(), obj.getAnuncios());
	}
}
