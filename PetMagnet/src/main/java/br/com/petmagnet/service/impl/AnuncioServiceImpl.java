package br.com.petmagnet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.exception.AppBeanNotFoundException;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.AnuncioProduto;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.model.Publicacao;
import br.com.petmagnet.repository.AnuncioRepository;
import br.com.petmagnet.service.AnuncioService;
import groovy.util.logging.Slf4j;

@Slf4j
@Service
@Transactional
public class AnuncioServiceImpl implements AnuncioService {
	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private AnuncioProdutoServiceImpl anuncioProdutoService;

	@Autowired
	private EstabelecimentoServiceImpl estabelecimentoService;

	@Autowired
	private ColaboradorServiceImpl colaboradorService;
	
	@Autowired
	private PublicacaoServiceImpl publicacaoService;

	@Override
	public Anuncio gravar(Anuncio obj) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(obj.getEstabelecimento().getId());

		Colaborador colaborador = this.colaboradorService
				.consultarPorColaborador(estabelecimento, obj.getColaborador().getId()).get();

		Anuncio anuncio = this.anuncioRepository
				.save(new Anuncio(null, obj.getTitulo(), obj.getDescricao(), null, estabelecimento, colaborador, null));

		List<AnuncioProduto> produtos = new ArrayList<AnuncioProduto>();

		for (AnuncioProduto produto : obj.getProdutos()) {
			produto.setAnuncio(anuncio);

			produtos.add(anuncioProdutoService.gravar(produto));
		}

		anuncio.setProdutos(produtos);

		return anuncio;
	}

	@Override
	public Anuncio excluir(Long idEstabelecimento, Long idAnuncio, Long idProduto) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento);

		Anuncio anuncio = this.anuncioRepository.findByEstabelecimentoAndId(estabelecimento, idAnuncio)
				.orElseThrow(() -> new AppBeanNotFoundException("Anúncio não Cadastrado"));

		if (idProduto == 0) {
			this.anuncioRepository.deleteById(idAnuncio);
		} else {
			this.anuncioProdutoService.excluir(idEstabelecimento, idAnuncio, idProduto);
		}

		return anuncio;
	}

	@Override
	public List<Anuncio> consultarTodos() {
		return this.anuncioRepository.findAll();
	}

	@Override
	public Optional<Anuncio> consultarPorId(Long idEstabelecimento, Long idAnuncio) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento);

		return Optional.ofNullable(this.anuncioRepository.findByEstabelecimentoAndId(estabelecimento, idAnuncio)
				.orElseThrow(() -> new AppBeanNotFoundException("Anúncio não Cadastrado")));
	}

	@Override
	public Anuncio alterar(Anuncio obj) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(obj.getEstabelecimento().getId());

		this.colaboradorService.consultarPorColaborador(estabelecimento, obj.getColaborador().getId()).get();

		// Atualiza o Anúncio

		Anuncio anuncio = this.anuncioRepository.findById(obj.getId())
				.orElseThrow(() -> new AppBeanNotFoundException("Anúncio não Cadastrado"));

		anuncio.setTitulo(obj.getTitulo());
		anuncio.setDescricao(obj.getDescricao());

		this.anuncioRepository.save(anuncio);

		// Atualiza os Produtos do Anúncio

		List<AnuncioProduto> produtosAlterados = new ArrayList<AnuncioProduto>();

		anuncio.getProdutos().stream().forEach(produtoAtual -> {
			for (AnuncioProduto produtoAlterado : obj.getProdutos()) {
				if (produtoAtual.getId().equals(produtoAlterado.getId())) {
					produtoAtual.setDescricao(produtoAlterado.getDescricao());
					produtoAtual.setPreco(produtoAlterado.getPreco());
					produtoAtual.setImagem(produtoAlterado.getImagem());

					produtosAlterados.add(produtoAtual);
				}
			}
		});

		this.anuncioProdutoService.gravarTodos(produtosAlterados);

		return anuncio;
	}

	@Override
	public List<Anuncio> consultarPorColaborador(Long idEstabelecimento, Long idColaborador, Boolean publicado) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento);

		Colaborador colaborador = this.colaboradorService.consultarPorId(idColaborador).get();

		List<Anuncio> anuncios = this.anuncioRepository.findByEstabelecimentoAndColaborador(estabelecimento,
				colaborador);
		List<Anuncio> anunciosNaoPaublicados = new ArrayList<Anuncio>();

		for (Anuncio anuncio : anuncios) {
			if (publicado) {
				if (anuncio.getPublicacoes().size() != 0)
					anunciosNaoPaublicados.add(anuncio);
			} else {
				if (anuncio.getPublicacoes().size() == 0)
					anunciosNaoPaublicados.add(anuncio);
			}
		}

		return anunciosNaoPaublicados;
	}

	@Override
	public List<Anuncio> consultarPorEstabelecimento(Long idEstabelecimento, Boolean publicado) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(idEstabelecimento);

		List<Anuncio> anuncios = this.anuncioRepository.findByEstabelecimento(estabelecimento);
		List<Anuncio> anunciosNaoPaublicados = new ArrayList<Anuncio>();

		for (Anuncio anuncio : anuncios) {
			if (publicado) {
				if (anuncio.getPublicacoes().size() != 0)
					anunciosNaoPaublicados.add(anuncio);
			} else {
				if (anuncio.getPublicacoes().size() == 0)
					anunciosNaoPaublicados.add(anuncio);
			}
		}

		return anunciosNaoPaublicados;
	}

	@Override
	public Anuncio publicarDireto(Anuncio obj, Date dtPublicacao, Date dtEncerramento) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(obj.getEstabelecimento().getId());

		Colaborador colaborador = this.colaboradorService
				.consultarPorColaborador(estabelecimento, obj.getColaborador().getId()).get();

		Anuncio anuncio = this.anuncioRepository
				.save(new Anuncio(null, obj.getTitulo(), obj.getDescricao(), null, estabelecimento, colaborador, null));

		List<AnuncioProduto> produtos = new ArrayList<AnuncioProduto>();

		for (AnuncioProduto produto : obj.getProdutos()) {
			produto.setAnuncio(anuncio);

			produtos.add(anuncioProdutoService.gravar(produto));
		}

		anuncio.setProdutos(produtos);
		
		// Realiza a PUBLICAÇÃO do anuncio.
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		
		anuncios.add(anuncio);
		
		Publicacao publicacao = new Publicacao(null, dtPublicacao, dtEncerramento, estabelecimento, colaborador, anuncios, Boolean.FALSE);
		
		publicacaoService.publicarSimples(publicacao);
		
		List<Publicacao> publicacoes = new ArrayList<Publicacao>();
		publicacoes.add(publicacao);
		
		anuncio.setPublicacoes(publicacoes);
		
		return anuncio;
	}
}
