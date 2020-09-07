package br.com.petmagnet.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public Colaborador cadastrar(@RequestBody Colaborador colaborador) {
		return this.colaboradorService.cadastrar(colaborador);
	}

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)	
	public Colaborador consultar(@PathVariable Long id) {
		return this.colaboradorService.consultarPorId(id).get();
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)	
	public Colaborador alterar(@PathVariable Long id, @RequestBody Colaborador novo) {
    	return this.colaboradorService.alterar(id, novo);
	}	

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)	
	public Colaborador excluir(@PathVariable Long id) {
    	return this.colaboradorService.excluir(id);
	}	
}
