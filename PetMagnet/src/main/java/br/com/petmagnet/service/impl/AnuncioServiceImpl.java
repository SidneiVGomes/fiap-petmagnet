package br.com.petmagnet.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.exception.BeanNotFoundException;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.repository.AnuncioRepository;
import br.com.petmagnet.service.AnuncioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class AnuncioServiceImpl implements AnuncioService {
	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private EstabelecimentoServiceImpl estabelecimentoService;

	@Autowired
	private ColaboradorServiceImpl colaboradorService;

	@Override
	public Anuncio cadastrar(Anuncio obj) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(obj.getEstabelecimento().getId());

		Colaborador colaborador = this.colaboradorService.consultarPorColaborador(estabelecimento, obj.getColaborador().getId()).get();
		
		return this.anuncioRepository.save(new Anuncio(null, obj.getTitulo(), obj.getDescricao(), null, estabelecimento, colaborador, null));
	}

	@Override
	public Anuncio excluir(Long idEstabelecimento, Long idAnuncio) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento);

		Anuncio anuncio = this.anuncioRepository.findByEstabelecimentoAndId(estabelecimento, idAnuncio)
				.orElseThrow(() -> new BeanNotFoundException("Anúncio não Cadastrado"));
		
		this.anuncioRepository.deleteById(idAnuncio);
		return anuncio;	
	}

	@Override
	public List<Anuncio> consultarTodos() {
		return null;
	}

	@Override
	public Optional<Anuncio> consultarPorId(Long idEstabelecimento, Long idAnuncio) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento);
		
		return Optional.ofNullable(this.anuncioRepository.findByEstabelecimentoAndId(estabelecimento, idAnuncio)
				.orElseThrow(() -> new BeanNotFoundException("Anúncio não Cadastrado")));
	}

	@Override
	public Anuncio alterar(Anuncio obj) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(obj.getEstabelecimento().getId());
		
		this.colaboradorService.consultarPorColaborador(estabelecimento, obj.getColaborador().getId()).get();
		
		Anuncio anuncio = this.anuncioRepository.findById(obj.getId())
				.orElseThrow(() -> new BeanNotFoundException("Anúncio não Cadastrado"));
		
		anuncio.setTitulo(obj.getTitulo());
		anuncio.setDescricao(obj.getDescricao());

		return this.anuncioRepository.save(anuncio);
	}

	@Override
	public List<Anuncio> consultarPorColaborador(Long idEstabelecimento, Long idColaborador) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento);
		
		Colaborador colaborador = this.colaboradorService.consultarPorId(idColaborador).get();
		
		return this.anuncioRepository.findByEstabelecimentoAndColaborador(estabelecimento, colaborador);
	}
}
