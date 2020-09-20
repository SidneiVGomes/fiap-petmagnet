package br.com.petmagnet.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.petmagnet.dto.AnuncioReqDTO;
import br.com.petmagnet.dto.AnuncioReqPublicacaoDTO;
import br.com.petmagnet.dto.AnuncioReqPutDTO;
import br.com.petmagnet.dto.AnuncioResDTO;
import br.com.petmagnet.dto.AnuncioResPublicacaoDTO;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.service.impl.AnuncioServiceImpl;

@RestController
@RequestMapping("API/anuncios")
public class AnuncioResource {
	@Autowired
	AnuncioServiceImpl anuncioService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public AnuncioResDTO cadastrar(@RequestBody AnuncioReqDTO anuncioDTO) {
		return new AnuncioResDTO(this.anuncioService.gravar(anuncioDTO.toEntity()));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/publicar", method = RequestMethod.POST)
	public AnuncioResPublicacaoDTO publicarDireto(@RequestBody AnuncioReqPublicacaoDTO anuncioDTO) {
		return new AnuncioResPublicacaoDTO(this.anuncioService.publicarDireto(anuncioDTO.toEntity(),
				anuncioDTO.getDtPublicacao(), anuncioDTO.getDtEncerramento()));
	}

//	@ResponseStatus(HttpStatus.CREATED)
//	@RequestMapping(value = "/teste_imagem", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//	public AnuncioResPublicacaoDTO publicarImagem(
//			@RequestBody final AnuncioReqPublicacaoDTO anuncioDTO,
//			@RequestPart("imageFile") final MultipartFile file) {
//		return new AnuncioResPublicacaoDTO(this.anuncioService.publicarImagem(anuncioDTO.toEntity(),
//				anuncioDTO.getDtPublicacao(), anuncioDTO.getDtEncerramento(), file));
//	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/uploadImagem", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Boolean publicarImagem(
			@RequestParam Long idEstabelecimento, @RequestParam Long idAnuncio, @RequestParam Long idProduto,
			@RequestPart("imageFile") final MultipartFile file) {
		return this.anuncioService.publicarImagem(idEstabelecimento, idAnuncio, idProduto, file);
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
