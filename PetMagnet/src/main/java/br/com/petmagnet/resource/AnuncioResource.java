package br.com.petmagnet.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		return new AnuncioResDTO(this.anuncioService.gravar((Anuncio) anuncioDTO.toEntity()));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/colaborador/{idColaborador}", method = RequestMethod.GET)
	public List<AnuncioResDTO> consultarAnunciosPorColaborador(@RequestParam Long idEstabelecimento,
			@PathVariable Long idColaborador, @RequestParam(defaultValue = "false") Optional<Boolean> publicado) {

		return new AnuncioResDTO(this.anuncioService.consultarPorColaborador(idEstabelecimento, idColaborador,
				publicado.orElse(Boolean.valueOf(false)))).toList();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/estabelecimento/{idEstabelecimento}", method = RequestMethod.GET)
	public List<AnuncioResDTO> consultarAnunciosPorEstabelecimento(@PathVariable Long idEstabelecimento,
			@RequestParam(defaultValue = "false") Optional<Boolean> publicado) {

		return new AnuncioResDTO(this.anuncioService.consultarPorEstabelecimento(idEstabelecimento,
				publicado.orElse(Boolean.valueOf(false)))).toList();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<AnuncioResDTO> consultarAnunciosPorColaborador() {
		return new AnuncioResDTO(this.anuncioService.consultarTodos()).toList();
	}	
		
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{idAnuncio}", method = RequestMethod.DELETE)
	public AnuncioResDTO excluir(@PathVariable Long idAnuncio, @RequestParam Long idEstabelecimento,
			@RequestParam Optional<Long> idProduto) {

		return new AnuncioResDTO(
				this.anuncioService.excluir(idEstabelecimento, idAnuncio, idProduto.orElse(Long.valueOf(0))));
	}

	@RequestMapping(value = "/{idAnuncio}", method = RequestMethod.PUT)
	public AnuncioResDTO alterar(@PathVariable Long idAnuncio, @RequestParam Long idEstabelecimento,
			@RequestParam Long idColaborador, @RequestBody AnuncioReqPutDTO anuncioDTO) {

		return new AnuncioResDTO(this.anuncioService
				.alterar((Anuncio) anuncioDTO.toEntity(idEstabelecimento, idColaborador, idAnuncio)));
	}
}
