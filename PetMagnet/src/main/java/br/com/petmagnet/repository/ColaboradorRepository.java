package br.com.petmagnet.repository;
 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
	Optional<Colaborador> findByNomeAndSenha(String nome, String senhas);
	Optional<Colaborador> findByNomeAndSenhaAndEstabelecimento(String nome, String senha, Estabelecimento estabelecimento);
	Optional<Colaborador> findByEstabelecimentoAndId(Estabelecimento estabelecimento, Long id);
}
