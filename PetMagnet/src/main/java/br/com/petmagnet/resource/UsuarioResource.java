package br.com.petmagnet.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.petmagnet.dto.UsuarioReqDTO;
import br.com.petmagnet.dto.UsuarioResDTO;
import br.com.petmagnet.model.Usuario;
import br.com.petmagnet.service.UsuarioService;

@RestController
@RequestMapping("API/usuarios")
public class UsuarioResource {
	@Autowired
	UsuarioService usuarioService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public UsuarioResDTO cadastrar(@RequestBody @Valid UsuarioReqDTO usuarioReqDTO) {
		return new UsuarioResDTO(this.usuarioService.gravar((Usuario) usuarioReqDTO.toEntity()));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public UsuarioResDTO registrar(@RequestParam String eMail, @RequestParam String CEP, @RequestParam Optional<Integer> alcanceKM) {
		return new UsuarioResDTO(this.usuarioService.registrar(eMail, CEP, alcanceKM.orElse(Integer.valueOf(5))));
	}
		
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UsuarioResDTO consultar(@PathVariable Long id) {
		return new UsuarioResDTO(this.usuarioService.consultarPorId(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<UsuarioResDTO> consultarTodos() {
		return new UsuarioResDTO(this.usuarioService.consultarTodos()).toList();
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public UsuarioResDTO alterar(@PathVariable Long id, @RequestBody UsuarioReqDTO usuarioReq) {
		return new UsuarioResDTO(this.usuarioService.alterar(id, usuarioReq.toEntity()));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public UsuarioResDTO excluir(@PathVariable Long id) {
		return new UsuarioResDTO(this.usuarioService.excluir(id));
	}
}
