package br.com.petmagnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.exception.AppBeanNotFoundException;
import br.com.petmagnet.model.Endereco;
import br.com.petmagnet.model.Usuario;
import br.com.petmagnet.repository.UsuarioRepository;
import br.com.petmagnet.service.UsuarioService;
import br.com.petmagnet.util.EnderecoTipo;
import groovy.util.logging.Slf4j;

@Slf4j
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EnderecoServiceImpl enderecoService;
	
	@Override
	public Usuario gravar(Usuario obj) {
		Endereco endereco = this.enderecoService.consultar(obj.getEndereco());
		
		if (endereco == null) {
			obj.getEndereco().setTipoEndereco(EnderecoTipo.PESSOA_FISICA.ordinal());
			
			endereco = this.enderecoService.gravar(obj.getEndereco());
		}
		
		obj.setEndereco(endereco);
		
		if (this.usuarioRepository.findByEmailAndSenha(obj.getEmail(), obj.getSenha()).isPresent()) {
			throw new AppBeanNotFoundException("Usuario já cadastrado");
		}

		return this.usuarioRepository.save(obj);
	}

	@Override
	public Usuario alterar(Long Id, Usuario obj) {
		obj.getEndereco().setTipoEndereco(EnderecoTipo.PESSOA_FISICA.ordinal());

		Endereco endereco = this.enderecoService.consultar(obj.getEndereco());
		
		if (endereco == null) {
			endereco = obj.getEndereco();
			
		} else {
			endereco.setTipoEndereco(obj.getEndereco().getTipoEndereco());
			endereco.setCep(obj.getEndereco().getCep());
			endereco.setUF(obj.getEndereco().getUF());
			endereco.setPais(obj.getEndereco().getPais());
			endereco.setLatitude(obj.getEndereco().getLatitude());
			endereco.setLongitude(obj.getEndereco().getLongitude());
		}

		endereco = this.enderecoService.gravar(endereco);
		
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
	public Usuario consultarPorId(Long id) {
		return this.usuarioRepository.findById(id)
				.orElseThrow(() -> new AppBeanNotFoundException("Usuario não Cadastrado"));
	}

	@Override
	public Usuario registrar(String eMail, String CEP, Integer alcanceKM) {
		Endereco endereco = this.enderecoService.consultarCEP(CEP);
		
		if (endereco == null) {
			endereco = this.enderecoService.gravar(
					new Endereco(null, ".", ".", ".", ".", ".", ".", CEP, null, null, EnderecoTipo.PESSOA_FISICA.ordinal())
				);
		}
		
		try {
			Usuario usuario = this.usuarioRepository.findByEmailAndSenha(eMail, "000000").get();
			
			usuario.setDistanciaAnuncio(alcanceKM);
			usuario.setEndereco(endereco);
			
			return this.usuarioRepository.save(usuario);
			
		} catch (Exception e) {
			return this.usuarioRepository.save(new Usuario(null, eMail, "00000", alcanceKM, endereco));
		}
	}
}
