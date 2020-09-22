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

import br.com.petmagnet.dto.PublicacaoReqDTO;
import br.com.petmagnet.dto.PublicacaoResDTO;
import br.com.petmagnet.model.Publicacao;
import br.com.petmagnet.service.PublicacaoService;

@RestController
@RequestMapping("API/publicacoes")
public class PublicacaoResource {
	@Autowired
	PublicacaoService publicacaoService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public PublicacaoResDTO publicar(@RequestBody PublicacaoReqDTO publicacao) {
		return new PublicacaoResDTO(this.publicacaoService.publicar((Publicacao) publicacao.toEntity()));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{idPublicacao}", method = RequestMethod.GET)
	public PublicacaoResDTO consultarId(@RequestParam Long idEstabelecimento, @PathVariable Long idPublicacao) {
		return new PublicacaoResDTO(this.publicacaoService.consultarPorId(idEstabelecimento, idPublicacao).get());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/estabelecimento/{idEstabelecimento}", method = RequestMethod.GET)
	public List<PublicacaoResDTO> consultarTodas(@PathVariable Long idEstabelecimento,
			@RequestParam(defaultValue = "false") Optional<Boolean> encerrado) {

		return new PublicacaoResDTO(
				this.publicacaoService.consultarTodos(idEstabelecimento, encerrado.orElse(Boolean.valueOf(false))))
						.toList();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/proximas", method = RequestMethod.GET)
	public List<PublicacaoResDTO> localizarPublicacoesAtivas(@RequestParam Optional<Long> idEndereco,
			@RequestParam Optional<Integer> alcanceKM) {

		return new PublicacaoResDTO(this.publicacaoService
				.localizarPublicacoesProximas(idEndereco.orElse(Long.valueOf(0)), alcanceKM.orElse(Integer.valueOf(0))))
						.toList();
	}

	@RequestMapping(value = "/cancelar/{idPublicacao}", method = RequestMethod.PUT)
	public PublicacaoResDTO alterar(@PathVariable Long idPublicacao, @RequestParam Long idEstabelecimento) {
		return new PublicacaoResDTO(this.publicacaoService.cancelar(idEstabelecimento, idPublicacao));
	}
}
