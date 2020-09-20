package br.com.petmagnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.exception.AppBeanNotFoundException;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.AnuncioProduto;
import br.com.petmagnet.repository.AnuncioProdutoRepository;
import br.com.petmagnet.service.AnuncioProdutoService;
import groovy.util.logging.Slf4j;

@Slf4j
@Service
@Transactional
public class AnuncioProdutoServiceImpl implements AnuncioProdutoService {
	@Autowired
	private AnuncioProdutoRepository anuncioProdutoRepository;
	
	@Autowired
	private AnuncioServiceImpl anuncioService;

	@Override
	public AnuncioProduto gravar(AnuncioProduto obj) {
		Anuncio anuncio = this.anuncioService.consultarPorId(
				obj.getAnuncio().getEstabelecimento().getId(), 
				obj.getAnuncio().getId()
			).get();

		AnuncioProduto produto = new AnuncioProduto(null, obj.getDescricao(), obj.getPreco(), obj.getImagem_byte(), anuncio);
		
		return this.anuncioProdutoRepository.save(produto);	
	}

	@Override
	public AnuncioProduto excluir(Long idEstabelecimento, Long idAnuncio, Long idProduto) {
		return this.anuncioProdutoRepository.findById(idProduto)
				.map(anuncioProdutos -> {
					this.anuncioProdutoRepository.deleteById(idProduto);
					return anuncioProdutos;
				}).orElseThrow(() -> new AppBeanNotFoundException("Produto não informado no anúncio."));
	}

	@Override
	public List<AnuncioProduto> consultarTodos() {
		return null;
	}

	@Override
	public AnuncioProduto consultarPorId(Long idAnuncio, Long idEstabelecimento, Long idProduto) {
		return this.anuncioProdutoRepository.findById(idProduto)
			.orElseThrow(() -> new AppBeanNotFoundException("Produto não informado no anúncio."));
	}

	@Override
	public AnuncioProduto alterar(Long idEstabelecimento, Long idAnuncio, Long idProduto, AnuncioProduto obj) {
		Anuncio anuncio = this.anuncioService.consultarPorId(idEstabelecimento, idAnuncio).get();
		
		for (AnuncioProduto produto : anuncio.getProdutos()) {
			if (produto.getId().equals(idProduto)) {

				produto.setDescricao(obj.getDescricao());
				produto.setImagem_byte(obj.getImagem_byte());
				produto.setPreco(obj.getPreco());
				
				return this.anuncioProdutoRepository.save(produto);
			}
		}
		
		throw new AppBeanNotFoundException("Produto não informado no anúncio.");
	}

	@Override
	public List<AnuncioProduto> gravarTodos(List<AnuncioProduto> obj) {
		obj.stream().forEach(produto -> {
			this.anuncioService.consultarPorId(produto.getAnuncio().getEstabelecimento().getId(), produto.getAnuncio().getId());
		});
		
		return this.anuncioProdutoRepository.saveAll(obj);
	}
}
