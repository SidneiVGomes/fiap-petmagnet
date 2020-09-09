package br.com.petmagnet.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.petmagnet.dto.ColaboradorReqDTO;
import br.com.petmagnet.dto.ColaboradorResDTO;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.service.ColaboradorService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("API/colaboradores")
public class ColaboradorResource {
	@Autowired
	ColaboradorService colaboradorService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public ColaboradorResDTO cadastrar(@RequestBody @Valid ColaboradorReqDTO colaboradorReqDTO) {
//		ColaboradorResDTO responseDTO = this.colaboradorService.convertToDTO(this.colaboradorService.cadastrar(colaboradorReqDTO));
//		return responseDTO;
		return new ColaboradorResDTO(this.colaboradorService.cadastrar((Colaborador) colaboradorReqDTO.toEntity()));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ColaboradorResDTO consultar(@PathVariable Long id) {
		return this.colaboradorService.convertToDTO(this.colaboradorService.consultarPorId(id).get());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ColaboradorResDTO alterar(@PathVariable Long id, @RequestBody ColaboradorReqDTO novo) {
		return this.colaboradorService.convertToDTO(this.colaboradorService.alterar(id, novo));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ColaboradorResDTO excluir(@PathVariable Long id) {
		return this.colaboradorService.convertToDTO(this.colaboradorService.excluir(id));
	}
}
