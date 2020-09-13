package br.com.petmagnet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
@Query("select u from Usuario u where u.email like :email")
public Usuario buscarByEmail(@Param("email") String email);

	@Query("select u from Usuario u "
		  + "join u.perfis p "
		  + "where u.email like :search% OR p.desc like :search%" )
public Page<Usuario> findByEmailOrPerfil(String search, Pageable pegeable);


	@Query("select u from Usuario u "
		  + "join u.perfis p "
		  + "where u.id = :usuarioId AND p.id IN  :perfisId" )	
public Usuario findByIdAndPerfis(Long usuarioId,Long[] perfisId);
}
