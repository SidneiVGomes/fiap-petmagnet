package br.com.petmagnet.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.petmagnet.dto.AnuncioReqDTO;
import br.com.petmagnet.dto.AnuncioReqPutDTO;
import br.com.petmagnet.dto.AnuncioResDTO;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.service.AnuncioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("API/anuncios")
public class AnuncioResource {
	@Autowired
	AnuncioService anuncioService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public AnuncioResDTO cadastrar(@RequestBody AnuncioReqDTO anuncioDTO) {
		return new AnuncioResDTO(this.anuncioService.cadastrar((Anuncio) anuncioDTO.toEntity()));
	}

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{idEstabelecimento}/{idAnuncio}", method = RequestMethod.GET)	
	public AnuncioResDTO consultar(@PathVariable Long idEstabelecimento, @PathVariable Long idAnuncio) {
		return new AnuncioResDTO(this.anuncioService.consultarPorId(idEstabelecimento, idAnuncio).get());
	}
	
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/colaborador/{idEstabelecimento}/{idColaborador}", method = RequestMethod.GET)	
	public List<AnuncioResDTO> consultarPorColaborador(@PathVariable Long idEstabelecimento, @PathVariable Long idColaborador) {
		return new AnuncioResDTO(this.anuncioService.consultarPorColaborador(idEstabelecimento, idColaborador)).toList();
	}	
	
    @RequestMapping(value = "/{idEstabelecimento}/{idColaborador}/{idAnuncio}", method = RequestMethod.PUT)	
	public AnuncioResDTO alterar(@PathVariable Long idEstabelecimento, @PathVariable Long idColaborador, @PathVariable Long idAnuncio, @RequestBody AnuncioReqPutDTO anuncioDTO) {
    	return new AnuncioResDTO(this.anuncioService.alterar((Anuncio) anuncioDTO.toEntity(idEstabelecimento, idColaborador, idAnuncio)));
	}	

    @RequestMapping(value = "/{idEstabelecimento}/{idAnuncio}", method = RequestMethod.DELETE)	
	public AnuncioResDTO excluir(@PathVariable Long idEstabelecimento, @PathVariable Long idAnuncio) {
    	return new AnuncioResDTO(this.anuncioService.excluir(idEstabelecimento, idAnuncio));
	}	
}
