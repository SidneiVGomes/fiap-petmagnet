package br.com.petmagnet.service;
 
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.petmagnet.model.Usuario;

@Service
public interface UsuarioService {
	public Usuario gravar(Usuario obj);
	public Usuario alterar(Long id, Usuario obj);
	public Usuario excluir(Long id);
	public Usuario consultarPorId(Long id);
	public List<Usuario> consultarTodos();
}
