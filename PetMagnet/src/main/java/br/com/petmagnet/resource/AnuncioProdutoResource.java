package br.com.petmagnet.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.petmagnet.dto.AnuncioProdutoReqDTO;
import br.com.petmagnet.dto.AnuncioProdutoReqPutDTO;
import br.com.petmagnet.dto.AnuncioProdutoResDTO;
import br.com.petmagnet.model.AnuncioProduto;
import br.com.petmagnet.service.AnuncioProdutoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("API/anuncios/produtos")
public class AnuncioProdutoResource {
	@Autowired
	AnuncioProdutoService anuncioProdutoService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public AnuncioProdutoResDTO cadastrar(@RequestBody AnuncioProdutoReqDTO produtoDTO) {
		return new AnuncioProdutoResDTO(this.anuncioProdutoService.cadastrar((AnuncioProduto) produtoDTO.toEntity()));
	}

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{idProduto}", method = RequestMethod.GET)	
	public AnuncioProdutoResDTO consultar(@PathVariable Long idProduto) {
		return new AnuncioProdutoResDTO(this.anuncioProdutoService.consultarPorId(idProduto));
	}
	
    @RequestMapping(value = "/{idEstabelecimento}/{idAnuncio}/{idProduto}", method = RequestMethod.PUT)	
	public AnuncioProdutoResDTO alterar(@PathVariable Long idEstabelecimento, @PathVariable Long idAnuncio, @PathVariable Long idProduto, @RequestBody AnuncioProdutoReqPutDTO produtoDTO) {
    	return new AnuncioProdutoResDTO(this.anuncioProdutoService.alterar(idEstabelecimento, idAnuncio, idProduto, (AnuncioProduto) produtoDTO.toEntity(idEstabelecimento, idAnuncio, idProduto)));
	}	

    @RequestMapping(value = "/{idProduto}", method = RequestMethod.DELETE)	
	public AnuncioProdutoResDTO excluir(@PathVariable Long idProduto) {
    	return new AnuncioProdutoResDTO(this.anuncioProdutoService.excluir(idProduto));
	}	
}
