package br.com.petmagnet.resource;

import java.util.List;

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

@RestController
@RequestMapping("API/colaboradores")
public class ColaboradorResource {
	@Autowired
	ColaboradorService colaboradorService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public ColaboradorResDTO cadastrar(@RequestBody @Valid ColaboradorReqDTO colaboradorReqDTO) {
		return new ColaboradorResDTO(this.colaboradorService.gravar((Colaborador) colaboradorReqDTO.toEntity()));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ColaboradorResDTO consultar(@PathVariable Long id) {
		return new ColaboradorResDTO(this.colaboradorService.consultarPorId(id).get());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<ColaboradorResDTO> consultarTodos() {
		return new ColaboradorResDTO(this.colaboradorService.consultarTodos()).toList();
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ColaboradorResDTO alterar(@PathVariable Long id, @RequestBody ColaboradorReqDTO novo) {
		return new ColaboradorResDTO(this.colaboradorService.alterar(id, novo));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ColaboradorResDTO excluir(@PathVariable Long id) {
		return new ColaboradorResDTO(this.colaboradorService.excluir(id));
	}
}
