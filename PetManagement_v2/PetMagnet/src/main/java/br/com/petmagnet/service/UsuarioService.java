package br.com.petmagnet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.petmagnet.model.Usuario;
import br.com.petmagnet.model.Usuario;

@Service
public interface UsuarioService {
	public Usuario gravar(Usuario obj);
	public Usuario excluir(Long id);
	public Usuario buscarPorId(Long idUsuario);
	public Usuario buscarPorIdEperfis(Long idUsuario,Long[] perfis);

}
