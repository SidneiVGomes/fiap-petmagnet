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

import br.com.petmagnet.dto.AnuncioDTO;
import br.com.petmagnet.dto.AnuncioResponseDTO;
import br.com.petmagnet.service.AnuncioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("API/anuncio")
public class AnuncioResource {
	@Autowired
	AnuncioService anuncioService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public AnuncioResponseDTO cadastrar(@RequestBody AnuncioDTO anuncio) {
		return this.anuncioService.convertParaDTO(
					this.anuncioService.cadastrar(anuncio)
				);
	}

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{idEstabelecimento}/{idAnuncio}", method = RequestMethod.GET)	
	public AnuncioResponseDTO consultar(@PathVariable Long idEstabelecimento, @PathVariable Long idAnuncio) {
		return this.anuncioService.convertParaDTO(
					this.anuncioService.consultarPorId(idEstabelecimento, idAnuncio).get()
				);
	}
	
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/colaborador/{idEstabelecimento}/{idColaborador}", method = RequestMethod.GET)	
	public List<AnuncioResponseDTO> consultarPorColaborador(@PathVariable Long idEstabelecimento, @PathVariable Long idColaborador) {
		return this.anuncioService.consultarPorColaborador(idEstabelecimento, idColaborador)
				.stream().map(anuncioService::convertParaDTO)
				.collect(Collectors.toList());
	}	
	
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)	
	public AnuncioResponseDTO alterar(@PathVariable Long id, @RequestBody AnuncioDTO anuncio) {
    	return this.anuncioService.convertParaDTO(
    				this.anuncioService.alterar(id, anuncio)
    			);
	}	

    @RequestMapping(value = "/{idEstabelecimento}/{idAnuncio}", method = RequestMethod.DELETE)	
	public AnuncioResponseDTO excluir(@PathVariable Long idEstabelecimento, @PathVariable Long idAnuncio) {
    	return this.anuncioService.convertParaDTO(
    				this.anuncioService.excluir(idEstabelecimento, idAnuncio)
    			);
	}	
}
