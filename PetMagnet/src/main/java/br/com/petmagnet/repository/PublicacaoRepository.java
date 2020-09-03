package br.com.petmagnet.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.model.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {
	Optional<Publicacao> findByEstabelecimentoAndId(Estabelecimento estabelecimento, Long idPublicacao);

	@Query(name = "findByPublicacoesAtivas",
		   value = "SELECT p FROM Publicacao p WHERE p.dtPublicacao <= :dtA and p.dtEncerramento >= :dtA")
	public List<Publicacao> findByPublicacoesAtivas(@Param("dtA") Date dtAtual);
}
