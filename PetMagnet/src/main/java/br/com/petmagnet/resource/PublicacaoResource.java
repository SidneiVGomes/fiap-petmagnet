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

import br.com.petmagnet.dto.PublicacaoAtivaResDTO;
import br.com.petmagnet.dto.PublicacaoReqDTO;
import br.com.petmagnet.model.Publicacao;
import br.com.petmagnet.service.PublicacaoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("API/publicacoes")
public class PublicacaoResource {
	@Autowired
	PublicacaoService publicacaoService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public Publicacao publicar(@RequestBody PublicacaoReqDTO publicacao) {
		return this.publicacaoService.publicar((Publicacao) publicacao.toEntity());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{idPublicacao}", method = RequestMethod.GET)
	public Publicacao consultarId(@RequestParam Long idEstabelecimento, @PathVariable Long idPublicacao) {
		return this.publicacaoService.consultarPorId(idEstabelecimento, idPublicacao).get();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<Publicacao> consultarTodas() {
		return this.publicacaoService.consultarTodos();
	}	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/proximas", method = RequestMethod.GET)
	public List<PublicacaoAtivaResDTO> localizarPublicacoesAtivas(@RequestParam Optional<Long> latitude,
			@RequestParam Optional<Long> longitude) {
		return new PublicacaoAtivaResDTO(this.publicacaoService
				.localizarPublicacoesProximas(latitude.orElse(Long.valueOf(0)), longitude.orElse(Long.valueOf(0))))
						.toList();
	}

	@RequestMapping(value = "/cancelar/{idPublicacao}", method = RequestMethod.PUT)
	public Publicacao alterar(@PathVariable Long idPublicacao, @RequestParam Long idEstabelecimento) {
		return this.publicacaoService.cancelar(idEstabelecimento, idPublicacao);
	}
}
