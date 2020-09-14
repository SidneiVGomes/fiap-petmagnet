package br.com.petmagnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.exception.AppBeanNotFoundException;
import br.com.petmagnet.model.Usuario;
import br.com.petmagnet.repository.UsuarioRepository;
import br.com.petmagnet.service.UsuarioService;
import groovy.util.logging.Slf4j;

@Slf4j
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario gravar(Usuario obj) {
		if (this.usuarioRepository.findByEmailAndSenha(obj.getEmail(), obj.getSenha()).isPresent()) {
			throw new AppBeanNotFoundException("Usuario já cadastrado");
		}

		obj.setId(null);

		return this.usuarioRepository.save(obj);
	}

	@Override
	public Usuario alterar(Long Id, Usuario obj) {
		return this.usuarioRepository.findById(Id).map(usuario -> {
			usuario.setSenha(obj.getSenha());
			usuario.setDistanciaAnuncio(obj.getDistanciaAnuncio());
			return this.usuarioRepository.save(usuario);

		}).orElseThrow(() -> new AppBeanNotFoundException("Usuario não cadastrado"));
	}

	@Override
	public Usuario excluir(Long id) {
		return this.usuarioRepository.findById(id).map(usuario -> {
			this.usuarioRepository.deleteById(id);
			return usuario;

		}).orElseThrow(() -> new AppBeanNotFoundException("Usuario não cadastrado"));
	}

	@Override
	public List<Usuario> consultarTodos() {
		return this.usuarioRepository.findAll();
	}

	@Override
//	public Optional<Usuario> consultarPorId(Long id) {
	public Usuario consultarPorId(Long id) {
		return this.usuarioRepository.findById(id)
				.orElseThrow(() -> new AppBeanNotFoundException("Usuario não Cadastrado"));
	}
}
