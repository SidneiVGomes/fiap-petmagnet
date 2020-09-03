package br.com.petmagnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petmagnet.model.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
	
}
