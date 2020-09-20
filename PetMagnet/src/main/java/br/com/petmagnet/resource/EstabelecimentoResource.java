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

import br.com.petmagnet.dto.EstabelecimentoReqDTO;
import br.com.petmagnet.dto.EstabelecimentoResDTO;
import br.com.petmagnet.service.impl.EstabelecimentoServiceImpl;

@RestController
@RequestMapping("API/estabelecimentos")
public class EstabelecimentoResource {
	@Autowired
	EstabelecimentoServiceImpl estabelecimentoService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public EstabelecimentoResDTO cadastrar(@RequestBody EstabelecimentoReqDTO estabelecimentoReqDTO) {
		return new EstabelecimentoResDTO(this.estabelecimentoService.gravar(estabelecimentoReqDTO.toEntity()));
	}

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)	
	public EstabelecimentoResDTO consultar(@PathVariable Long id) {
		return new EstabelecimentoResDTO(this.estabelecimentoService.consultarPorId(id));
	}

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)	
	public List<EstabelecimentoResDTO> consultarTodos() {
		return new EstabelecimentoResDTO(this.estabelecimentoService.consultarTodos()).toList();
	}	
	
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)	
	public EstabelecimentoResDTO alterar(@PathVariable Long id, @RequestBody EstabelecimentoReqDTO estabelecimentoReqDTO) {
    	return new EstabelecimentoResDTO(this.estabelecimentoService.alterar(id, estabelecimentoReqDTO.toEntity()));
	}	

//	@ResponseStatus(HttpStatus.ACCEPTED)
//	@RequestMapping(value = "/logotipo", method = RequestMethod.PUT, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//	public Boolean registrarLogotipo(@RequestParam Long idEstabelecimento, @RequestPart("imageFile") final MultipartFile file) {
//		return this.estabelecimentoService.registrarLogo(idEstabelecimento, file);
//	}		
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)	
	public EstabelecimentoResDTO excluir(@PathVariable Long id) {
    	return new EstabelecimentoResDTO(this.estabelecimentoService.excluir(id));
	}	
}
