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
	public AnuncioProduto cadastrar(@RequestBody AnuncioProdutoReqDTO produto) {
		return this.anuncioProdutoService.cadastrar(produto);
	}

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)	
	public AnuncioProduto consultar(@PathVariable Long id) {
		return this.anuncioProdutoService.consultarPorId(id);
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)	
	public AnuncioProduto alterar(@PathVariable Long id, @RequestBody AnuncioProdutoReqDTO produto) {
    	return this.anuncioProdutoService.alterar(id, produto);
	}	

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)	
	public AnuncioProduto excluir(@PathVariable Long id) {
    	return this.anuncioProdutoService.excluir(id);
	}	
}
