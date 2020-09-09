package br.com.petmagnet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petmagnet.model.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
	Optional<Estabelecimento> findByCnpj(String cnpj);
}
