package br.com.petmagnet.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.petmagnet.model.Usuario;
import br.com.petmagnet.service.UsuarioService;



@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {

	
	@Autowired
	private UsuarioService service; 
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity find(@PathVariable Long id) {
			Usuario obj = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	/*@RequestBody = Faz o java transformar os dados em JSON PARA UMA CLASSE JAVA
	 * ServletUriCOmponents = Faz retornar para o objeto criado
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void>insert(@RequestBody Usuario obj){
		obj = service.gravar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
/*	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Usuario obj, @PathVariable Integer id){
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
*/	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		service.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	
	/* Stream para listar  as Usuario da Lista original  instanciar o DTO da
	 * Usuario, e trtanformar em uma nova lista os collector 
	 
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>>buscarTodos() {
		List <Usuario> list  =	service.buscarTodos();
		List <UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());;
		return ResponseEntity.ok().body(listDto);
		
	}	
	*/
	
}
