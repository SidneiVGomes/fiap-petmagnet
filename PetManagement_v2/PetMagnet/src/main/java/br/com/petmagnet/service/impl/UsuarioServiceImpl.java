package br.com.petmagnet.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.exception.BeanNotFoundException;
import br.com.petmagnet.model.Perfil;
import br.com.petmagnet.model.Usuario;
import br.com.petmagnet.repository.UsuarioRepository;
import br.com.petmagnet.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepository UsuarioRepository;
	
	
	//@Transactional(readOnly = true)
	public Usuario buscarPorEmail(String email) {	
		return UsuarioRepository.buscarByEmail(email);		
	}
	
	@Override
	public Usuario gravar(Usuario obj) {
		return this.UsuarioRepository.save(obj);	
	}
	

	@Override
	public Usuario excluir(Long IdUsuario) {
		return this.UsuarioRepository.findById(IdUsuario)
				.map(Usuarios -> {
					this.UsuarioRepository.deleteById(IdUsuario);
					return Usuarios;
				}).orElseThrow(() -> new BeanNotFoundException("Usuario não Localizado."));
	}


	@Override
	public Usuario buscarPorIdEperfis(Long usuarioId, Long[] perfisId ){
		return UsuarioRepository.findByIdAndPerfis(usuarioId, perfisId);
	}

		@Override
		public Usuario buscarPorId(Long id) {
		
			Optional<Usuario> obj = UsuarioRepository.findById(id);
			return obj.orElseThrow(() -> new BeanNotFoundException("Usuário não Localizado"));
		}
		
			@Override 
			@Transactional(readOnly =true)
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Usuario usuario = buscarPorEmail(username);
			return new User(
					usuario.getEmail(),
					usuario.getSenha(),
					AuthorityUtils.createAuthorityList(getAtuthorities(usuario.getPerfis()))				
					);
			
				}

		private String[] getAtuthorities(List<Perfil> perfis) {
			String[] authorities = new String[perfis.size()];
			for (int i = 0; i < perfis.size(); i++) {
				authorities[i] = perfis.get(i).getDesc();
			}
			return authorities;
		}

	
	
	
}
