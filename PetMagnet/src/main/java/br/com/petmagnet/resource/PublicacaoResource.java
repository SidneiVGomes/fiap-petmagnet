package br.com.petmagnet.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.petmagnet.dto.PublicacaoDTO;
import br.com.petmagnet.dto.PublicacaoResponseAtivaDTO;
import br.com.petmagnet.model.Publicacao;
import br.com.petmagnet.service.PublicacaoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("API/publicacao")
public class PublicacaoResource {
	@Autowired
	PublicacaoService publicacaoService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public Publicacao publicar(@RequestBody PublicacaoDTO publicacao) {
		return this.publicacaoService.publicar(publicacao);
	}

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{idEstabelecimento}/{idPublicacao}", method = RequestMethod.GET)	
	public Publicacao consultarId(@PathVariable Long idEstabelecimento, @PathVariable Long idPublicacao) {
		return this.publicacaoService.consultarPorId(idEstabelecimento, idPublicacao).get();
	}

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ativas", method = RequestMethod.GET)	
	public List<PublicacaoResponseAtivaDTO> localizarPublicacoesAtivas() {
		return this.publicacaoService.localizarPublicacoesAtivas()
					.stream().map(publicacaoService::convertParaDTO)
					.collect(Collectors.toList());
	}	
	
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)	
	public Publicacao alterar(@PathVariable Long id, @RequestBody PublicacaoDTO publicacao) {
    	return this.publicacaoService.alterar(id, publicacao);
	}	

    @RequestMapping(value = "/{idEstabelecimento}/{idPublicacao}", method = RequestMethod.DELETE)	
	public Publicacao excluir(@PathVariable Long idEstabelecimento, @PathVariable Long idPublicacao) {
    	return this.publicacaoService.excluir(idEstabelecimento, idPublicacao);
	}	
}
