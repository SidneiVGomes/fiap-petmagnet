package br.com.petmagnet.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.petmagnet.service.AnuncioProdutoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("API/anuncios/produtos")
public class AnuncioProdutoResource {
	@Autowired
	AnuncioProdutoService anuncioProdutoService;
	
//	@ResponseStatus(HttpStatus.CREATED)
//	@RequestMapping(method = RequestMethod.POST)
//	public AnuncioProdutoResDTO cadastrar(@RequestBody AnuncioProdutoReqDTO produtoDTO) {
//		return new AnuncioProdutoResDTO(this.anuncioProdutoService.cadastrar((AnuncioProduto) produtoDTO.toEntity()));
//	}
//
//	@ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/{idP}/Anuncio/{idA}/Estabelecimento/{idE}", method = RequestMethod.GET)	
//	public AnuncioProdutoResDTO consultar(@PathVariable Long idA, @PathVariable Long idE, @PathVariable Long idP) {
//		return new AnuncioProdutoResDTO(this.anuncioProdutoService.consultarPorId(idA, idE, idP));
//	}
//	
//    @RequestMapping(value = "/{idEstabelecimento}/{idAnuncio}/{idProduto}", method = RequestMethod.PUT)	
//	public AnuncioProdutoResDTO alterar(@PathVariable Long idEstabelecimento, @PathVariable Long idAnuncio, @PathVariable Long idProduto, @RequestBody AnuncioProdutoReqPutDTO produtoDTO) {
//    	return new AnuncioProdutoResDTO(this.anuncioProdutoService.alterar(idEstabelecimento, idAnuncio, idProduto, (AnuncioProduto) produtoDTO.toEntity(idEstabelecimento, idAnuncio, idProduto)));
//	}	
//
//    @RequestMapping(value = "/{idEstabelecimento}/{idAnuncio}/{idProduto}", method = RequestMethod.DELETE)	
//	public AnuncioProdutoResDTO excluir(@PathVariable Long idEstabelecimento, @PathVariable Long idAnuncio, @PathVariable Long idProduto) {
//    	return new AnuncioProdutoResDTO(this.anuncioProdutoService.excluir(idEstabelecimento, idAnuncio, idProduto));
//	}	
}
