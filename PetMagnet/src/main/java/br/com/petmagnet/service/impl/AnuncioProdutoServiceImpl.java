package br.com.petmagnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.dto.AnuncioProdutoReqDTO;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.AnuncioProduto;
import br.com.petmagnet.repository.AnuncioProdutoRepository;
import br.com.petmagnet.service.AnuncioProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class AnuncioProdutoServiceImpl implements AnuncioProdutoService {
	@Autowired
	private AnuncioProdutoRepository anuncioProdutoRepository;
	
	@Autowired
	private AnuncioServiceImpl anuncioService;

	@Override
	public AnuncioProduto cadastrar(AnuncioProdutoReqDTO obj) {
		Anuncio anuncio = this.anuncioService.consultarPorId(obj.getIdEstabelecimento(), obj.getIdAnuncio()).get();
		
		if (anuncio == null) {
			return null;
		}
		
		AnuncioProduto produto = new AnuncioProduto(null, obj.getDescricao(), obj.getPreco(), obj.getImagem(), anuncio);
		
		return this.anuncioProdutoRepository.save(produto);	
	}

	@Override
	public AnuncioProduto excluir(Long id) {
		return this.anuncioProdutoRepository.findById(id)
				.map(anuncioProdutos -> {
					this.anuncioProdutoRepository.deleteById(id);
					return anuncioProdutos;
				}).orElseThrow(() -> new IllegalStateException("Este Produto não está informado no anúncio."));
	}

	@Override
	public List<AnuncioProduto> consultarTodos() {
		return null;
	}

	@Override
	public AnuncioProduto consultarPorId(Long id) {
		return this.anuncioProdutoRepository.findById(id).orElseThrow(() -> new IllegalStateException("Este Produto não está informado no anúncio."));
	}

	@Override
	public AnuncioProduto alterar(Long id, AnuncioProdutoReqDTO obj) {
		AnuncioProduto produto = this.anuncioProdutoRepository.findById(id).orElseThrow(() -> new IllegalStateException("Este Produto não está informado no anúncio."));

		produto.setAnuncio(this.anuncioService.consultarPorId(obj.getIdEstabelecimento(), obj.getIdAnuncio()).get());
		produto.setDescricao(obj.getDescricao());
		produto.setImagem(obj.getImagem());
		produto.setPreco(obj.getPreco());
		
		return this.anuncioProdutoRepository.save(produto);
	}
}
